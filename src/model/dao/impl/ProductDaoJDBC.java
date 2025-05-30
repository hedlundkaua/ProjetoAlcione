package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.entities.Fornecedor;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao {
	
	private Connection conn;
	
	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO product "
					+ "(Name, QntdProduct, PrecoProd, ValidadeProd, FornecedorId) "
					+ "VALUES "
					+ "(?,?,?,?,?) ", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getQntd());
			st.setDouble(3, obj.getPreco());
			st.setDate(4, new Date(obj.getValidade().getTime()));
			st.setInt(5, obj.getFornecedor().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
					if(rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
					}
				DB.colseResultSet(rs);
			}
		}
		catch (SQLException e) {
			throw new DbException("Error unexpected! No rows affected");
		}
		finally {
			DB.closeStatment(st);
		}
		
		
	}

	@Override
	public void update(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE product "
					+ "SET Name = ?, QntdProduct = ?, PrecoProd = ?, ValidadeProd = ?, FornecedorId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getQntd());
			st.setDouble(3, obj.getPreco());
			st.setDate(4, new Date(obj.getValidade().getTime()));
			st.setInt(5, obj.getFornecedor().getId());
			st.setInt(6, obj.getId());
			
			int rowsAffected = st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
		}		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM product WHERE Id = ?");
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			if(rows == 0) {
				throw new DbException("Linha não existe!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
		}
		
		
	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT product.*, fornecedor.name as ByName, fornecedor.* "
					+ "FROM product INNER JOIN fornecedor "
					+ "ON product.FornecedorId = fornecedor.id "
					+ "WHERE product.id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if(rs.next()) {
				Fornecedor  forn = instantiateFornecedor(rs);
				
				Product product = instantiateProduct(rs, forn);
				
				return product;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.colseResultSet(rs);
		}	
	}
	@Override
	public List<Product> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT product.* , fornecedor.name AS ByName, fornecedor.* "
					+ "FROM product INNER JOIN fornecedor "
					+ "ON product.FornecedorId = fornecedor.id "
					+ "ORDER BY product.name");
			
			rs = st.executeQuery();
			
			List<Product> list = new ArrayList<>();
			Map<Integer, Fornecedor> map = new HashMap<>();
			
			while(rs.next()) {
				
				Fornecedor fornecedor = map.get(rs.getInt("FornecedorId"));
				
				if(fornecedor == null) {
					fornecedor = instantiateFornecedor(rs);
					
					map.put(rs.getInt("FornecedorId"), fornecedor);
				}
				
				Product obj = instantiateProduct(rs, fornecedor);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.colseResultSet(rs);
		}
	}

	@Override
	public List<Product> findByFornecedor(Fornecedor obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT product.* , fornecedor.name as ByName, fornecedor.* "
					+ "FROM product INNER JOIN fornecedor "
					+ "ON product.FornecedorId = fornecedor.id "
					+ "WHERE FornecedorId = ?");

			st.setInt(1, obj.getId());
			rs = st.executeQuery();
			
			List<Product> list = new ArrayList<Product>();
			Map<Integer, Fornecedor> map = new HashMap<>();
			
			while(rs.next()) {
				Fornecedor fornecedor = map.get(rs.getInt("FornecedorId"));
				
				if(fornecedor == null) {
					fornecedor = instantiateFornecedor(rs);
					
					map.put(rs.getInt("FornecedorId"), fornecedor);
				}
				Product product = instantiateProduct(rs, fornecedor);
				list.add(product);	
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.colseResultSet(rs);
		}
		
		
	}

	private Product instantiateProduct(ResultSet rs, Fornecedor forn) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setNome(rs.getString("Name"));
		product.setQntd(rs.getInt("QntdProduct"));
		product.setPreco(rs.getDouble("PrecoProd"));
		product.setValidade(rs.getDate("ValidadeProd"));
		product.setFornecedor(forn);
		return product;
	}
	
	private Fornecedor instantiateFornecedor(ResultSet rs) throws SQLException {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(rs.getInt("FornecedorId"));
		fornecedor.setNome(rs.getString("ByName"));
		fornecedor.setCnpj(rs.getString("Cnpj"));
		fornecedor.setEmail(rs.getString("Email"));
		return fornecedor;
	}
	
}

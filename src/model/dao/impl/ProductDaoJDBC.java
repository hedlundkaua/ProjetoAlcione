package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT product.*, fornecedor.* "
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByFornecedor(Fornecedor obj) {
		// TODO Auto-generated method stub
		return null;
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
		fornecedor.setNome(rs.getString("Name"));
		fornecedor.setCnpj(rs.getString("Cnpj"));
		fornecedor.setEmail(rs.getString("Email"));
		return fornecedor;
	}
	
}

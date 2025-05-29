package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.FornecedorDao;
import model.entities.Fornecedor;

public class FornecedorDaoJDBC implements FornecedorDao {
	
	private Connection conn;
	
	public FornecedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Fornecedor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO fornecedor"
					+"(Name, Cnpj, Email)"
					+ "VALUES"
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getCnpj());
			st.setString(3, obj.getEmail());
			
			int rowsAffected = st.executeUpdate();
			//Apos isso vamos adicionar as Ids
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
					if(rs.next()) {
						int id = rs.getInt(1); //Obtém o valor da primeira coluna de resultSet
						obj.setId(id); //define o Id do obj
					}
				DB.colseResultSet(rs);
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
	public void update(Fornecedor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE fornecedor "
					+ "SET Name = ?, Cnpj = ?, Email = ? " // UPDATE SET!!!
					+ "WHERE Id = ?");
			st.setString(1, obj.getNome());
			st.setString(2,obj.getCnpj());
			st.setString(3, obj.getEmail());
			st.setInt(4, obj.getId()); //Tem que pegar o ID também!!
			
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
					"DELETE FROM fornecedor WHERE id = ?");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("Linha não existe!!");
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
	public Fornecedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM fornecedor WHERE id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if(rs.next()) {
				Fornecedor forn = instantmentFornecedor(rs);
				return forn;
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
	public List<Fornecedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM fornecedor");
			rs = st.executeQuery();
			
			List<Fornecedor> list = new ArrayList<Fornecedor>();
			while(rs.next()) {
				Fornecedor forn = instantmentFornecedor(rs);
				
				list.add(forn);
			}
			DB.colseResultSet(rs);
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
	
	private Fornecedor instantmentFornecedor(ResultSet rs) throws SQLException {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(rs.getInt("id"));
		fornecedor.setNome(rs.getString("Name"));
		fornecedor.setCnpj(rs.getString("Cnpj"));
		fornecedor.setEmail(rs.getString("Email"));
		return fornecedor;
	}
	

}

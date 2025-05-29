package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fornecedor findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fornecedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

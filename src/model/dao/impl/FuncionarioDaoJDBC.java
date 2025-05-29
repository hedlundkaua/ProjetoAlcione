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
import model.dao.FuncionarioDao;
import model.entities.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao{
	
	private Connection conn;
	
	//Construtor!!
	public FuncionarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public void insert(Funcionario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO funcionario "
					+ "(Name, BirthDate, BaseSalary, CargoId) "
					+ "VALUES "
					+ "(?,?,?,?) ", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setDate(2, new Date(obj.getBirthDate().getTime()));
			st.setDouble(3, obj.getSalario());
			st.setInt(4, obj.getCargo().getId());// pega a classe Cargo e depois o Id de cargo!!
			
			int rows = st.executeUpdate();
			if(rows > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.colseResultSet(rs);				
			}else {
				throw new DbException("Unexpected error! No rows affected");
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
	public void update(Funcionario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Funcionario findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

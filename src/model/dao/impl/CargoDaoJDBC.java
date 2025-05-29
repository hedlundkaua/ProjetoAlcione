package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CargoDao;
import model.entities.Cargo;

public class CargoDaoJDBC implements CargoDao{
	
	private Connection conn;
	
	public CargoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Cargo obj) {

		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO cargo"
					+ "(Name)"
					+ "VALUES" //<- VALUES!!!
					+ "(?)",
					//retorna uma key
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				//pega uma key generada
				ResultSet rs = st.getGeneratedKeys();
					if(rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
					}
					DB.colseResultSet(rs);
			}else {
				throw new DbException("Unexpected erros! No rows affeccted");
			
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatment(st);
		}
	}

	@Override
	public void update(Cargo obj) {
		PreparedStatement st = null;
	
		try {
			st = conn.prepareStatement(
					"UPDATE cargo "
					+ "SET Name = ? "
					+ "WHERE Id = ?");
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());
			
			int rowsAffected = st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"DELETE "
					+ "FROM cargo "
					+ "WHERE id = ? ");
			st.setInt(1, id);
			
			int arrowsAffected = st.executeUpdate();	
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
		}
	}

	@Override
	public Cargo findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM cargo WHERE id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if(rs.next()) {
				//a partir da query que fizemos no banco instanciamos um objeto Cargo
				Cargo cargo = instantiateCargo(rs);
				
				return cargo;
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
	public List<Cargo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM cargo");
			
			rs = st.executeQuery();
			
			List<Cargo> list = new ArrayList<>();
			
			while(rs.next()) {
				 Cargo cargo = instantiateCargo(rs);
				list.add(cargo);
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
	
	
	private Cargo instantiateCargo(ResultSet rs) throws SQLException {
		Cargo cargo = new Cargo();
		cargo.setId(rs.getInt("id"));
		cargo.setNome(rs.getString("Name"));
		return cargo;
	}
	
	
}

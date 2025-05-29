package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CargoDao findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CargoDao> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Cargo instantiateCargo(ResultSet rs) throws SQLException {
		Cargo car = new Cargo();
		car.setId(rs.getInt("id"));
		car.setNome(rs.getString("Nome"));
		return car;
	}

}

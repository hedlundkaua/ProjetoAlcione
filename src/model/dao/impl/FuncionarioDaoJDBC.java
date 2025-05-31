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
import model.dao.FuncionarioDao;
import model.entities.Cargo;
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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE funcionario "
					+ "SET Name = ?, BirthDate = ?, BaseSalary = ?, CargoId = ? "
					+ "WHERE funcionario.Id = ?");
			
			st.setString(1, obj.getNome());
			st.setDate(2, new Date(obj.getBirthDate().getTime()));
			st.setDouble(3, obj.getSalario());
			st.setInt(4, obj.getCargo().getId());
			st.setInt(5, obj.getId());
			
			int rows = st.executeUpdate();
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
					"DELETE FROM funcionario WHERE id = ?");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				System.out.println("Linha não existe!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT funcionario.*, cargo.Name as ByName "
					+ "FROM funcionario INNER JOIN cargo "
					+ "ON funcionario.CargoId = cargo.id "
					+ "WHERE funcionario.id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();		
			if(rs.next()) {
				Cargo cargo = instantiateCargo(rs);
				
				Funcionario funcionario = instatiateFuncionario(rs, cargo);
				
				return funcionario;
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
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT funcionario.*, cargo.name as ByName "
					+ "FROM funcionario INNER JOIN cargo "
					+ "ON funcionario.CargoId = cargo.id "
					+ "ORDER BY Name ");
		
			rs = st.executeQuery();
			
			List<Funcionario> list = new ArrayList<>();
			Map<Integer, Cargo> map = new HashMap<>();
			
			while(rs.next()) {
				Cargo cargo = map.get(rs.getInt("CargoId"));
				
				if(cargo == null) {
					cargo = instantiateCargo(rs);
					
					map.put(rs.getInt("CargoId"), cargo);
				}
				Funcionario obj = instatiateFuncionario(rs, cargo);
				list.add(obj);
			}
			return list;			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private Funcionario instatiateFuncionario(ResultSet rs, Cargo cargo) throws SQLException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(rs.getInt("id"));
		funcionario.setNome(rs.getString("Name"));
		funcionario.setBirthDate(rs.getDate("BirthDate"));
		funcionario.setSalario(rs.getDouble("BaseSalary"));
		funcionario.setCargo(cargo);
		return funcionario;
	}
	
	private Cargo instantiateCargo(ResultSet rs) throws SQLException {
		Cargo cargo = new Cargo();
		cargo.setId(rs.getInt("CargoId"));
		cargo.setNome(rs.getString("ByName"));
		return cargo;
	}


	@Override
	public List<Funcionario> findByCargo(Cargo cargo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT funcionario.*, cargo.name as ByName, cargo.* "
					+ "FROM funcionario INNER JOIN cargo "
					+ "ON funcionario.CargoId = cargo.id "
					+ "WHERE CargoId = ?");
			st.setInt(1, cargo.getId());
			
			rs = st.executeQuery();
			
				List<Funcionario> list = new ArrayList<>();
				Map<Integer, Cargo> map = new HashMap<>();
								
				while(rs.next()) {
					Cargo car = map.get(rs.getInt("CargoId"));
					if(car == null) {
						car = instantiateCargo(rs);
						
						map.put(rs.getInt("CargoId"), car);
					}
					Funcionario func = instatiateFuncionario(rs, car);
					list.add(func);
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
	

}

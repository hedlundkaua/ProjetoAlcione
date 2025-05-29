package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.CargoDao;

public class CargoDaoJDBC implements CargoDao{
	
	private Connection conn;
	
	public CargoDaoJDBC(Connection conn) {
		this.conn = conn;
	}


	@Override
	public void insert(CargoDao obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CargoDao obj) {
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

}

package model.dao;

import java.util.List;

import model.entities.Cargo;

public interface CargoDao {
	
	void insert(Cargo obj);
	void update(Cargo obj);
	void deleteById(Integer id);
	CargoDao findById(Integer id);
	List<CargoDao> findAll();
}

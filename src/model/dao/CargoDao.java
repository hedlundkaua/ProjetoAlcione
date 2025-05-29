package model.dao;

import java.util.List;

public interface CargoDao {
	
	void insert(CargoDao obj);
	void update(CargoDao obj);
	void deleteById(Integer id);
	CargoDao findById(Integer id);
	List<CargoDao> findAll();
}

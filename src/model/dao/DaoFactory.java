package model.dao;

import db.DB;
import model.dao.impl.CargoDaoJDBC;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.FuncionarioDaoJDBC;
import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {
		
	public static CargoDao createCargoDao() {
		return new CargoDaoJDBC(DB.getConnection());
	}
	
	public static FornecedorDao createFornecedorDao() {
		return new FornecedorDaoJDBC(DB.getConnection());
	}
	
	public static ProductDao createProducDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static FuncionarioDao createFuncionarioDao(){
		return new FuncionarioDaoJDBC(DB.getConnection());
	}
	
}

package model.dao.impl;

import java.sql.Connection;
import java.util.List;

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
		// TODO Auto-generated method stub
		
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

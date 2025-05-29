package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.entities.Cargo;
import model.entities.Funcionario;

public class FuncionarioTest {
	public static void main(String[] args) {
		
		FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();
		
		Cargo cargo = new Cargo(3, null);
		Funcionario funcionario = new Funcionario(null, "Edo Bernardo", new Date(11/12/1975) , 2300.0, cargo);
		funcionarioDao.insert(funcionario);
		System.out.println(funcionario);
		
		
	}
}

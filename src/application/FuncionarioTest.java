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
		
		
		
		
		//System.out.println("== Insert funcionario ==");
		//Cargo cargo = new Cargo(1, null);
		//Funcionario funcionario = new Funcionario(null, "Joao", new Date(11/12/1972) , 1300.0, cargo);
		//funcionarioDao.insert(funcionario);
		
		//System.out.println("== findById Funcionario ==");
		//Funcionario newfun = funcionarioDao.findById(5);
		//System.out.println(newfun);
		
	}
}

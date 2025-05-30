package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
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
		
		//System.out.println("== findAll funcionario ==");
		//List<Funcionario> list = funcionarioDao.findAll();
		//for(Funcionario f : list) {
		//	System.out.println(f);
		//}
		
		//System.out.println("== Update funcionario ==");
		//Funcionario fu = new Funcionario();
		//fu = funcionarioDao.findById(4);
		//fu.setNome("Bruno");
		//funcionarioDao.update(fu);
		
		//System.out.println("== Delete funcionario ==");
		//funcionarioDao.deleteById(2);	
		
		System.out.println("Hello world");
	}
}

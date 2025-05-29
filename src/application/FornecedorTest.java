package application;

import model.dao.DaoFactory;
import model.dao.FornecedorDao;

public class FornecedorTest {
	public static void main(String[] args) {
	
							//DaoFactory -> cria funcionaroDao
		
		FornecedorDao forDao = DaoFactory.createFornecedorDao();
		
		
		//System.out.println("== Insert Fornecedor ==");
		//Fornecedor newForn = new Fornecedor(null, "SEFAZ", "33.513.165/0001-85", "sefaz@sefaz.rs.gov.br");
		//forDao.insert(newForn);
		//System.out.println(newForn);
		
		//System.out.println("== Update Fornecedor ==");
		//Fornecedor forNew = new Fornecedor(1, "Hedlund", "12.123.123/0001-12", "matted@gmail.com");
		//forDao.update(forNew);
	
		//System.out.println("== Delete Fornecedor ==");
		//forDao.deleteById(1);
		
	
	}
}

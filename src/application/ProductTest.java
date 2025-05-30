package application;



import java.util.Date;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Fornecedor;
import model.entities.Product;

public class ProductTest {

	public static void main(String[] args) {
		
		ProductDao productDao = DaoFactory.createProducDao();
		
		
		
		//System.out.println("== Insert product ==");
		//Fornecedor fornecedor = new Fornecedor(2, "Matted", "1234", "matted@gmail.com");
		//Product product = new Product(null, "Massa Isabella", 500, 2000.0, new Date(20/06/2025) , fornecedor);
		//productDao.insert(product);
		
		//System.out.println("== findById product ==");
		//Product prod = productDao.findById(1);
		//System.out.println(prod);
		
		

	}

}

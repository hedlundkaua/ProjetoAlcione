package application;



import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Fornecedor;
import model.entities.Product;

public class ProductTest {

	public static void main(String[] args) {
		
		ProductDao productDao = DaoFactory.createProducDao();
		
		
		
		//System.out.println("== Insert product ==");
		Fornecedor fornecedor = new Fornecedor(2, null,null, null);
		//Product product = new Product(null, "Maça", 150, 2000.0, new Date(20/06/2025) , fornecedor);
		//productDao.insert(product);
		
		//System.out.println("== findById product ==");
		//Product prod = productDao.findById(2);
		//System.out.println(prod);
		
		//System.out.println("\n== findAll Product ==");
		//List<Product> list = productDao.findAll();
		//for(Product p : list) {
		//	System.out.println(p);
		//}
		
		//System.out.println("== findByFornecedor product == ");
		//List<Product> list = productDao.findByFornecedor(fornecedor);
		//for(Product p : list) {
		//	System.out.println(p);
		//}
		
		//Product prod = new Product();
		//System.out.println("== Update product ==");
		//prod = productDao.findById(3);
		//prod.setNome("Banana");
		//productDao.update(prod);
		
		
		

	}

}

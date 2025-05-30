package application;

import java.util.List;

import model.dao.CargoDao;
import model.dao.DaoFactory;
import model.entities.Cargo;

public class CargoTest {
	public static void main(String[] args) {
		
		
		CargoDao cargoDao = DaoFactory.createCargoDao();
		
		Cargo newcargo = new Cargo(null, "Motorista");
		
		
		//System.out.println("== Insert Cargo ==");
		//cargoDao.insert(newcargo);
		//System.out.println("Created Cargo = " + newcargo);
		
		//System.out.println("== Update Cargo ==");
		//Cargo upCargo = new Cargo(1, "Auxiliar");
		//cargoDao.update(upCargo);
		
		//System.out.println("== Delete Cargo ==");
		//cargoDao.deleteById(2);
		
		//System.out.println("== findById Cargo ==");
		//Cargo car = cargoDao.findById(1);
		//System.out.println(car);
		
		//System.out.println("== findAll Cargo ==");
		//List<Cargo> all = cargoDao.findAll();
		//for(Cargo c : all) {
		//	System.out.println(c);
		//}
		
		
		
			
	}
}

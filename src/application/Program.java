package application;

import model.dao.CargoDao;
import model.dao.DaoFactory;
import model.entities.Cargo;

public class Program {
	public static void main(String[] args) {
		
		
		CargoDao cargoDao = DaoFactory.createCargoDao();
		
		Cargo newcargo = new Cargo(null, "Motorista");
		
		
		//System.out.println("== Insert Cargo ==");
		//cargoDao.insert(newcargo);
		//System.out.println("Created Cargo = " + newcargo);
		
		//System.out.println("== Update Cargo ==");
		//Cargo upCargo = new Cargo(1, "Auxiliar");
		//cargoDao.update(upCargo);
		
		System.out.println("== Delete Cargo ==");
		cargoDao.deleteById(2);
		
		
		
		
	}
}

package com.dealership.car.logic;

import com.dealership.car.persistence.PersistenceController;
import java.util.List;


public class Controller {
    
    PersistenceController persistenceController = new PersistenceController();
    
    public void saveCar(String brand, String model, String engine, String color, String patent, int amountDoors) {
        
        Car newCar = new Car();
        newCar.setBrand(brand);
        newCar.setModel(model);
        newCar.setEngine(engine);
        newCar.setColor(color);
        newCar.setPatent(patent);
        newCar.setAmountDoors(amountDoors);
        
        persistenceController.saveCar(newCar);
    }


    public List<Car> getCars() {
        return persistenceController.getCars();
    }


    public void deleteCar(int carId) {
        persistenceController.deleteCar(carId);
    }

    public Car getCarById(int carId) {
        return persistenceController.getCarById(carId);
    }

    public void updateCar(Car car,String brand, String model, String engine, String color, String patent, int amountDoors) {

       car.setBrand(brand);
       car.setModel(model);
       car.setEngine(engine);
       car.setColor(color);
       car.setPatent(patent);
       car.setAmountDoors(amountDoors);
       
       
       persistenceController.updateCar(car);
    }
    
}

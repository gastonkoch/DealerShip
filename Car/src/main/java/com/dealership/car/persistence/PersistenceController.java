package com.dealership.car.persistence;

import com.dealership.car.logic.Car;
import com.dealership.car.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenceController {
    CarJpaController carJpa = new CarJpaController();

    public void saveCar(Car newCar) {
        carJpa.create(newCar);
    }

    public List<Car> getCars() {
        return carJpa.findCarEntities();
    }

    public void deleteCar(int carId) {
        try {
            carJpa.destroy(carId);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Car getCarById(int carId) {
        return carJpa.findCar(carId);
    }

    public void updateCar(Car car) {
        try {
            carJpa.edit(car);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




}

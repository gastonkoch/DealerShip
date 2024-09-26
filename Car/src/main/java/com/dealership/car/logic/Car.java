package com.dealership.car.logic;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String brand;
    private String model;
    private String engine;
    private String color;
    private String patent;
    private int amountDoors;

    public Car() {
    }

    public Car(int id, String brand, String model, String engine, String color, String patent, int amountDoors) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.color = color;
        this.patent = patent;
        this.amountDoors = amountDoors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public int getAmountDoors() {
        return amountDoors;
    }

    public void setAmountDoors(int amountDoors) {
        this.amountDoors = amountDoors;
    }
    
    
}

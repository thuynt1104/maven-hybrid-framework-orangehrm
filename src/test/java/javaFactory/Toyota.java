package javaFactory;

import org.testng.annotations.Test;

public class Toyota extends Car{
    @Override
    public void viewCar() {
        System.out.println("View Toyota Car");
    }
    @Override
    public void driveCar() {
        System.out.println("Drive Toyota Car");
    }
}

package javaFactory;

public class Ford extends Car{
    @Override
    public void viewCar() {
        System.out.println("View Ford Car");
    }

    @Override
    public void driveCar() {
        System.out.println("Drive Ford Car");
    }
}

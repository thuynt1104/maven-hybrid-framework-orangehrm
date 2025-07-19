package javaFactory;

public class EndUser {
    public static void main(String[] args) {
        //Car Factory
        Car car = null;

        //Morning
        car = getCar("Honda");
        car.viewCar();
        car.driveCar();

        //Afternoon
        car = getCar("Toyota");
        car.viewCar();
        car.driveCar();

        car = getCar("Huyndai");
        car.viewCar();
        car.driveCar();
    }

    public static Car getCar(String carName){
        Car car = null;
        switch (carName){
            case "Toyota":
                car = new Toyota();
                break;
            case "Honda":
                car = new Honda();
                break;
            case "Huyndai":
                car = new Huyndai();
                break;
            case "Ford":
                car = new Ford();
                break;
            default:
                throw new RuntimeException("Car Name is not supported");
        }
        return car;
    }
}

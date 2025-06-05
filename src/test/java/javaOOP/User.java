package javaOOP;

public class User {
    public static void main(String[] args) {
        //Đươc phép khởi tạo từ bên ngoài
        //Object/Instance ( cho phép khởi tạo nhiều class)
        Car car = new Car();
        car.setFullName();
        car.fullName = "abc";

        Car secoundCar = new Car();

        //khởi tạo
        Computer computer = new Computer();
        computer.setRAM();

        //Tao person
        Person firstEmp = new Person("Nguyễn Thị Thúy","Hà Nội","20");
        Person secondEmp = new Person("Nguyễn Thị Thúy","Hà Nội","20");

    }
}

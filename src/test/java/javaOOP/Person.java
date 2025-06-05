package javaOOP;

public class Person {
    String name;
    String age;
    String address;

    public Person(String name, String address, String age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

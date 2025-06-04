package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Class ke thua class
// class ke thua interface : implements
public class Topic_01_Keywords extends Topic_06 implements Topic_02{

    //chi co non-abstract method thoi
    //khong duoc co abstract method
    //khoi tao binh thuong
    //cho phep ke thua

    //Data type
     String a = "a"; //char
     byte bNumber = 10;
     short sNumber = 11;
     int iNumber = 12;
     long lNumber = 13;
     float fNumber = 14.99F;
     double dNumber = 15.99D;
     boolean bBoolean = true;

     String fullName = null;

     //Asset Modifier

    // Variable
    private String studentName = "Students";
    String studentID = "StudentID";
    protected int studentAge = 20;
    public double studentPoint = 12.2;

    //Method
    //Ham khong co kieu tra ve
    private void TC_01(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Topic_01_Keywords topic = new Topic_01_Keywords();
    }
    void TC_02(){}
    protected void TC_03(){}
    public void TC_04(){}

    @Override
    public void showStudent() {
        Topic_02.super.showStudent();
    }

    @Override
    public void clearStudent() {
        // tu implement function tai day
    }

    //Class/interface/enum


}

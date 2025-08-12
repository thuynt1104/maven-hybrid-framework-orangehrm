package pageUIs;

public class AddEmployeePageUI {
    public static final String FIRST_NAME_TEXTBOX = "//input[@name='firstName']";
    public static final String LAST_NAME_TEXTBOX = "//input[@name='lastName']";
    public static final String ID_TEXTBOX = "//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON = "//button[contains(string(),'Save')]";
    public static final String SPINNER_ICON = "//div[@class='oxd-loading-spinner']";
}

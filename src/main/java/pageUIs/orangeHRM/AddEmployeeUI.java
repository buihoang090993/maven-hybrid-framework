package pageUIs.orangeHRM;

public class AddEmployeeUI {
    public static final String FIRST_NAME_TEXTBOX = "//input[@name='firstName']";
    public static final String MIDDLE_NAME_TEXTBOX = "//input[@name='middleName']";
    public static final String LAST_NAME_TEXTBOX = "//input[@name='lastName']";
    public static final String EMPLOYEE_ID_TEXTBOX = "//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String CREATE_LOGIN_DETAILS_BUTTON = "//p[text()='Create Login Details']/following-sibling::div/label/span";
    public static final String USER_NAME_TEXTBOX = "//label[text()='Username']/parent::div/following-sibling::div/input";
    public static final String PASSWORD_TEXTBOX = "//label[text()='Password']/parent::div/following-sibling::div/input";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "//label[text()='Confirm Password']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON = "//button[contains(string(),'Save')]";
    public static final String SUCCESSFUL_SAVE_MESSAGE = "//p[text()='Successfully Saved']";
}

package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01HomeScreen extends PageBase {
    private final By loginHeader = By.xpath("(//*[normalize-space()='Login'])[1]");
    private final By usernameInput = By.xpath("//*[@name='username']");
    private final By passwordInput = By.xpath("//*[@name='password']");
    private final By submitLoginCTA = By.xpath("(//*[normalize-space()='Login'])[3]");
    private final By dashboardTitle = By.xpath("(//*[normalize-space()='Dashboard'])[5]");

    public P01HomeScreen(WebDriver driver) {
        super(driver);
    }

    public void checkLoginScreen() {
        waitForVisibilityOfElement(loginHeader);
        ElementsValidator(usernameInput, passwordInput, submitLoginCTA);
    }

    public void checkLoginSuccessfully(String username, String password) {
        sendTextToInputField(username, usernameInput);
        sendTextToInputField(password, passwordInput);
        clickOnElement(submitLoginCTA);
    }

    public void checkDashboardDisplayed() {
        waitForVisibilityOfElement(dashboardTitle);
    }

}

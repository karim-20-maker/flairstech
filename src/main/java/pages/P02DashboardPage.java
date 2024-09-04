package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class P02DashboardPage extends PageBase {
    public P02DashboardPage(WebDriver driver) {
        super(driver);
    }

    String randomPassword = "Password@" + generateRandomDigits(4);
    String username = "usernameName" + generateRandomDigits(4);
    Actions actions = new Actions(driver);

    int numberOfRecordsBeforeCreation = 0;
    int numberOfRecordsAfterCreation = 0;
    int numberOfRecordsBeforeDeleting = 0;
    int numberOfRecordsAfterDeleting = 0;
    private final By adminFromSideMenu = By.xpath("(//*[normalize-space()='Admin'])[2]");
    private final By numberOfRecordsLabel = By.xpath("(//*[contains(normalize-space(),' Records Found')])[11]");
    private final By addRecordCTA = By.xpath("//button[contains(normalize-space(),'Add')]");
    private final By selectRoleDropdownCTA = By.xpath("(//*[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]");
    private final By selectStatus = By.xpath("(//*[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]");
    private final By employeeNameInput = By.xpath("//*[@placeholder='Type for hints...']");
    private final By usernameInput = By.xpath("(//*[@autocomplete='off'])[1]");
    private final By passwordInput = By.xpath("(//*[@autocomplete='off'])[2]");
    private final By confirmPasswordInput = By.xpath("(//*[@autocomplete='off'])[3]");
    private final By saveCTA = By.xpath("//button[normalize-space()='Save']");
    private final By deleteIcon = By.xpath("//*[@class='oxd-icon bi-trash']");
    private final By searchByUserName = By.xpath("(//*[@class='oxd-input oxd-input--active'])[2]");
    private final By confirmDelete = By.xpath("//*[normalize-space()='Yes, Delete']");
    private final By searchCTA = By.xpath("//*[@type='submit' and contains(normalize-space(),'Search')]");

    public void checkRecordsScreen() {
        clickOnElement(adminFromSideMenu);
        waitForVisibilityOfElement(numberOfRecordsLabel);
        ElementsValidator(numberOfRecordsLabel, addRecordCTA);
    }

    public void checkRecordCreation(String employeeName) {
        numberOfRecordsBeforeCreation = extractNumberOfRecords(driver.findElement(numberOfRecordsLabel).getText());
        clickOnElement(addRecordCTA);
        selectRule();
        selectEmployeeName(employeeName);
        selectStatus();
        sendTextToInputField(username, usernameInput);
        sendTextToInputField(randomPassword, passwordInput);
        sendTextToInputField(randomPassword, confirmPasswordInput);
        clickOnElement(saveCTA);
        waitForVisibilityOfElement(numberOfRecordsLabel);
        numberOfRecordsAfterCreation = extractNumberOfRecords(driver.findElement(numberOfRecordsLabel).getText());

    }

    public void validateRecordCreatedSuccessfully() {
        Assert.assertTrue(numberOfRecordsAfterCreation > numberOfRecordsBeforeCreation);
        System.out.println("before==> " + numberOfRecordsBeforeCreation + ", and after==>" + numberOfRecordsAfterCreation);
    }

    private void selectEmployeeName(String employeeName) {
        sendTextToInputField(employeeName, employeeNameInput);
        waitForTime(5000);
        waitForVisibilityOfElement(By.xpath("//*[normalize-space()='" + employeeName + "']"));
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();

    }

    private void selectRule() {
        clickOnElement(selectRoleDropdownCTA);
        waitForTime(5000);
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
    }

    private void selectStatus() {
        actions.moveToElement(driver.findElement(selectStatus)).click().perform();
        waitForTime(5000);
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void validateDeleteRecordsFunctionality() {
        numberOfRecordsBeforeDeleting = extractNumberOfRecords(driver.findElement(numberOfRecordsLabel).getText());
        waitForVisibilityOfElement(searchByUserName);
        sendTextToInputField(username, searchByUserName);
        clickOnElement(searchCTA);
        waitForVisibilityOfElement(deleteIcon);
        clickOnElement(deleteIcon);
        clickOnElement(confirmDelete);
        waitForVisibilityOfElement(numberOfRecordsLabel);
        numberOfRecordsAfterDeleting = extractNumberOfRecords(driver.findElement(numberOfRecordsLabel).getText());
    }

    public void validateDeletingSuccessfully() {
        Assert.assertTrue(numberOfRecordsBeforeDeleting > numberOfRecordsAfterDeleting);
        System.out.println("before==> " + numberOfRecordsBeforeDeleting + ", and after==>" + numberOfRecordsAfterDeleting);

    }

}

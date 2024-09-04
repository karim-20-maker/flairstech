package stepdefinitions;

import Common.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.P01HomeScreen;
import pages.P02DashboardPage;

import static Common.ReadProperties.*;

public class MyStepDefs extends Hooks {
    private P01HomeScreen home;
    private P02DashboardPage dashboard;

    @Before
    public void startDriver() {
        startDriverSession();
        home = new P01HomeScreen(driver);
        dashboard = new P02DashboardPage(driver);

    }

    @After
    public void tearDown() {
        tearDownDriver();
    }


    @Given("the user navigate to open source demo")
    public void theUserNavigatesToOpenSourceDemo() {
        home.checkLoginScreen();
    }

    @When("user login with valid creds")
    public void userLogsInWithValidCreds() {
        home.checkLoginSuccessfully(username, password);
    }


    @Then("dashboard should displayed successfully")
    public void dashboardShouldDisplayedSuccessfully() {
        home.checkDashboardDisplayed();
    }

    @And("user navigating to admin screen")
    public void userNavigatingToAdminScreen() {
        dashboard.checkRecordsScreen();
    }

    @When("user try to create record with valid data")
    public void userTryToCreateRecordWithValidData() {
        dashboard.checkRecordCreation(employeeName);

    }

    @Then("record should created successfully")
    public void recordShouldCreatedSuccessfully() {
        dashboard.validateRecordCreatedSuccessfully();

    }

    @And("when user try to delete recode")
    public void whenUserTryToDeleteRecode() {
        dashboard.validateDeleteRecordsFunctionality();
    }

    @Then("record should be deleted successfully")
    public void recordShouldBeDeletedSuccessfully() {
        dashboard.validateDeletingSuccessfully();

    }
}

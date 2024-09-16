package stepdefinitions;

import apis.APIsTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.io.IOException;

public class APIsStepDef {
    APIsTest apis = new APIsTest();


    @Given("user should be able to create user from apis")
    public void userShouldBeAbleToCreateUserFromApis() throws IOException {
        apis.loginToGetCookies();
        apis.createUser();
    }

    @And("can delete user too from apis")
    public void canDeleteUserTooFromApis() throws IOException {
        apis.deleteUser();
    }
}

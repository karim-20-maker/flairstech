package apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.*;

import java.io.IOException;
import java.util.Collections;

import static Common.ReadProperties.APIURL;
import static base.PageBase.generateRandomDigits;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class APIsTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private int userId = 0;
    String sessionCookie;
    public void loginToGetCookies() throws JsonProcessingException {
        RestAssured.baseURI = "https://opensource-demo.orangehrmlive.com";

        // Create the login request object
        LoginRequest loginRequest = new LoginRequest(
                "8123a50ccba180f9ca67248.lix3xs1tqTrkJkxWXwI4IWeos_7E_hnI7xQzBhukzgE._UVBoIE_32qxThwSGXJycSPC9r-QqGuNvXUKa2zim07HXxD-_SfYYLEQCQ",
                "Admin",
                "admin123"
        );

        // Login request (POST request)
        Response loginResponse = given().log().all()
                .contentType("application/x-www-form-urlencoded")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header("cookie", "orangehrm=445520ee61cc83798e070931584e958b; orangehrm=ed38aff654c2fb46cc286d331cc77004")
                .formParam("_token", loginRequest.getToken())
                .formParam("username", loginRequest.getUsername())
                .formParam("password", loginRequest.getPassword())
                .when()
                .post("/web/index.php/auth/login");

        assertThat(loginResponse.getStatusCode(), equalTo(200));
        String sessionCookie = loginResponse.getCookie("orangehrm");
    }

    public void createUser() throws JsonProcessingException, IOException {
        RestAssured.baseURI = APIURL;
        CreateUserRequest newUser = new CreateUserRequest("username" + generateRandomDigits(4), "Testing@1", true, 1, 1);
        String userPayload = objectMapper.writeValueAsString(newUser);
        System.out.println(userPayload);
        Response createResponse = given().log().all()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("cookie", "orangehrm=" +  sessionCookie)
                .header("user-agent","Mozilla/5.0 (Linux; Android) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.109 Safari/537.36 CrKey/1.54.248666")
                .body(userPayload)
                .post();

        assertThat(createResponse.getStatusCode(), equalTo(201));

        CreateUserResponse createUserResponse = objectMapper.readValue(createResponse.asString(), CreateUserResponse.class);
        userId = createUserResponse.getData().getId();
    }

    public void deleteUser() throws JsonProcessingException, IOException {
        DeleteUserRequest deleteRequest = new DeleteUserRequest(Collections.singletonList(userId));
        String deletePayload = objectMapper.writeValueAsString(deleteRequest);

        Response deleteResponse = given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("cookie", "orangehrm=" +  sessionCookie)
                .body(deletePayload)
                .delete();

        assertThat(deleteResponse.getStatusCode(), equalTo(200));
        DeleteUserResponse deleteUserResponse = objectMapper.readValue(deleteResponse.asString(), DeleteUserResponse.class);
        assertThat(deleteUserResponse.getMessage(), notNullValue());
    }
}

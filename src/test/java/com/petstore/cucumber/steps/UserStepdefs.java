package com.petstore.cucumber.steps;

import com.petstore.petstoreinfo.UserSteps;
import com.petstore.utils.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class UserStepdefs {

    static int id = TestUtils.getRandomNum();
    static String username = "Pratik78" + TestUtils.getRandomValue();
    static String firstName = "Pratik";
    static String lastName = "Patel";
    static String email = "ppatel" + TestUtils.getRandomValue() + "@email.com";
    static String password = "Ppatel78" + TestUtils.getRandomNum();
    static String phone = "075" + TestUtils.getRandomPhone();
    static int userStatus = 1;

    static int userID;

    @Steps
    UserSteps userSteps;
    @Given("^Petstore app is running$")
    public void petstoreAppIsRunning() {
    }

    @When("^I create a new user by providing userID \"([^\"]*)\" username \"([^\"]*)\" firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" password \"([^\"]*)\" phone \"([^\"]*)\" userStatus \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingUserIDUsernameFirstNameLastNameEmailPasswordPhoneUserStatus(int _id, String _username, String _firstName, String lastName, String _email,
                                                                                                      String password, String phone, int userStatus) {

        ValidatableResponse response = userSteps.createUser(id, username, firstName, lastName, email, password, phone, userStatus);
        response.log().all().statusCode(200);
        response.extract().path("id");
    }

    @Then("^user must get back a valid status code 200")
    public void userMustGetBackAValidStatusCode() {

    }

    @When("^I send GET request for userID \"([^\"]*)\"$")
    public void iSendGETRequestForUserID(String _userID){
        HashMap<String, Object> userMap = userSteps.getUserInfoByUserId(username);
        Assert.assertThat(userMap, hasValue(username));
        userID = (int) userMap.get("id");
    }

    @Then("^I should get back a valid status code 200")
    public void iShouldGetBackAValidStatusCode() {
    }

    @When("^I update user by providing userID \"([^\"]*)\" username \"([^\"]*)\" firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" password \"([^\"]*)\" phone \"([^\"]*)\" userStatus \"([^\"]*)\"$")
    public void iUpdateUserByProvidingUserIDUsernameFirstNameLastNameEmailPasswordPhoneUserStatus(int _id, String _username, String _firstName, String lastName, String _email,
                                                                                                  String password, String phone, int userStatus) {

        firstName = firstName + "pendra";
        ValidatableResponse response = userSteps.updateUser(id, username, firstName, lastName, email, password, phone, userStatus);
        response.log().all().statusCode(200);


        HashMap<String, Object> userMap = userSteps.getUserInfoByUserId(username);
        Assert.assertThat(userMap, hasValue(userID));

    }

    @Then("^I should get a valid status code 200")
    public void iShouldGetAValidStatusCode() {

    }

    @When("^I send delete request by userID \"([^\"]*)\"$")
    public void iSendDeleteRequestByUserID(String userID) {
        userSteps.deleteUser(username).statusCode(200);
        userSteps.getUserInfoAfterDeletion(username).statusCode(404);

    }

    @Then("^user get deleted and get status code 200")
    public void userGetDeletedAndGetStatusCode() {
    }
}

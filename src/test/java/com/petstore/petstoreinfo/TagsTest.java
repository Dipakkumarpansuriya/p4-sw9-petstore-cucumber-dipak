package com.petstore.petstoreinfo;

import com.petstore.constants.EndPoints;
import com.petstore.model.UserPojo;
import com.petstore.testbase.TestBase;
import com.petstore.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {

    static int id = TestUtils.getRandomNum();
    static String username = "Pratik78" + TestUtils.getRandomValue();
    static String firstName = "Pratik";
    static String lastName = "Patel";
    static String email = "ppatel" + TestUtils.getRandomValue() + "@email.com";
    static String password = "Ppatel78" + TestUtils.getRandomNum();
    static String phone = "075" + TestUtils.getRandomPhone();
    static int userStatus = 1;

    static String userID;

    @Steps
    UserSteps userSteps;

    @Test
    public void test001() {

        UserPojo userPojo = new UserPojo();

        userPojo.setId(id);
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);


        SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then().log().all().statusCode(200);

        HashMap<String, Object> userMap = userSteps.getUserInfoByUserId(username);
        Assert.assertThat(userMap, hasValue(username));
        userID = (String) userMap.get("username");

    }

    @Test
    public void test002() {
         SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .pathParam("userID", userID)
                .get(EndPoints.GET_USER_BY_ID)
                .then().log().all().statusCode(200)
                .extract()
                .path("");

    }

    @WithTag("Petstorefeature:NEGATIVE")
    @Title("Provide 500 status code when incorrect Http method is used to access resource")
    @Test
    public void test003() {

        SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .pathParam("userID", userID)
                .put(EndPoints.GET_USER_BY_ID)
                .then().log().all().statusCode(500)
                .extract()
                .path("");
    }


    @WithTag("Petstorefeature:POSITIVE")
    @Title("Provide 200 status code when correct Http method is used to access resource")
    @Test
    public void test004() {

        SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .pathParam("userID", userID)
                .get(EndPoints.GET_USER_BY_ID)
                .then().log().all().statusCode(200)
                .extract()
                .path("");
    }
}

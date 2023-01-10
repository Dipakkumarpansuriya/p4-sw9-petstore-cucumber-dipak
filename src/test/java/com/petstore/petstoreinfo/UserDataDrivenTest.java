package com.petstore.petstoreinfo;

import com.petstore.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/java/resources/testdata/userinfo.csv")
@Concurrent(threads = "2x")
public class UserDataDrivenTest extends TestBase {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    @Steps
    UserSteps userSteps;

    @Title("Data driven test for adding multiple users to the application")
    @Test
    public void createMultipleUsers() {

        userSteps.createUser(id, username, firstName, lastName, email, password, phone, userStatus);

    }

}

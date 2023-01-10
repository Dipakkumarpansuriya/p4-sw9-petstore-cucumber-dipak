package com.petstore.cucumber.steps;

import com.petstore.petstoreinfo.PetSteps;
import com.petstore.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class PetStepdefs extends TestUtils{


    static int id = TestUtils.getRandomNum();
    static String name = "Cow" + TestUtils.getRandomValue();
    static String status = "owned";

    static int petID;
    @Steps
    PetSteps petSteps;
    @When("^I create a new Pet by providing petID \"([^\"]*)\" name \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewPetByProvidingPetIDNameStatus(int _id, String _name, String _status){

        HashMap<Object, Object> newCategory = new HashMap<>();
        newCategory.put("name", "Cow");
        newCategory.put("id", 4);

        List<Object> photoUrl = new ArrayList<>();
        photoUrl.add("https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQ0zKfLDmM1fhNHY6NDwWUPwiE3eHxo1WN--LPEj9R_ygXnyLnODKX3ePliQ8LohO7nhjFqaK3om04&usqp=CAc");


        List<HashMap<Object, Object>> tagList = new ArrayList<>();
        HashMap<Object, Object> tagHash = new HashMap<>();
        tagHash.put("id", 1);
        tagHash.put("name", "Domesticated");
        tagList.add(tagHash);


        ValidatableResponse response = petSteps.createDataForNewPet(id, newCategory, name, photoUrl, tagList, status);
        response.log().all().statusCode(200);


    }

    @Then("^I must get back a valid status code 200")
    public void iMustGetBackAValidStatusCode() {
    }

    @When("^I send GET request for petID \"([^\"]*)\"$")
    public void iSendGETRequestForPetID(String _petID) {

        HashMap<String, Object> petMap = petSteps.findPetById(id);
        Assert.assertThat(petMap, hasValue(id));
        petID = (int) petMap.get("id");

    }

    @Then("^I must get back valid status code 200")
    public void iMustGetBackValidStatusCode() {
    }

    @When("^I update a Pet by providing petID \"([^\"]*)\" name \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iUpdateAPetByProvidingPetIDNameStatus(int id, String _name, String status) {

        name = name + "_updated";

        HashMap<Object, Object> newCategory = new HashMap<>();
        newCategory.put("id", 5);
        newCategory.put("name", "Cat");

        List<Object> photoUrl = new ArrayList<>();
        photoUrl.add("https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQ0zKfLDmM1fhNHY6NDwWUPwiE3eHxo1WN--LPEj9R_ygXnyLnODKX3ePliQ8LohO7nhjFqaK3om04&usqp=CAc");


        List<HashMap<Object, Object>> tagList = new ArrayList<>();
        HashMap<Object, Object> tagHash = new HashMap<>();
        tagHash.put("name", "Domesticated");
        tagHash.put("id", 1);
        tagList.add(tagHash);


        ValidatableResponse response = petSteps.updateDataForPet(petID, newCategory, name, photoUrl, tagList, status);
        response.log().all().statusCode(200);
    }

    @Then("^I must get a valid status code 200")
    public void iMustGetAValidStatusCode() {
    }

    @When("^I send delete request by petID \"([^\"]*)\"$")
    public void iSendDeleteRequestByPetID(String _petID) {

        petSteps.deletePet(petID).statusCode(200);
        petSteps.findPetByIdAfterDeletion(petID).statusCode(404);

    }

    @Then("^pet detail get deleted and get status code 200")
    public void petDetailGetDeletedAndGetStatusCode() {
    }
}

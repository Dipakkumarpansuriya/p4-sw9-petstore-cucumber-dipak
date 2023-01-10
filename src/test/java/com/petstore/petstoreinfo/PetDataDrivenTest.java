package com.petstore.petstoreinfo;

import com.petstore.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/java/resources/testdata/petinfo.csv")
@Concurrent(threads = "2x")
public class PetDataDrivenTest extends TestBase {

    static int id;
    static String name;
    static String status;

    @Steps
    PetSteps petSteps;


    @Title("Data driven test for adding multiple pets to the application")
    @Test
    public void createMultiplePets() {

        HashMap<Object, Object> newCategory = new HashMap<>();
        newCategory.put("name", "Cow");
        newCategory.put("id", 4);

        List<Object> photoUrl = new ArrayList<>();
        photoUrl.add("https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQ0zKfLDmM1fhNHY6NDwWUPwiE3eHxo1WN--LPEj9R_ygXnyLnODKX3ePliQ8LohO7nhjFqaK3om04&usqp=CAc");


        List<HashMap<Object, Object>> tagList = new ArrayList<>();
        HashMap<Object, Object> tagHash = new HashMap<>();
        tagHash.put("id", 1);
        tagHash.put("name", "");
        tagList.add(tagHash);


        petSteps.createDataForNewPet(id, newCategory, name, photoUrl, tagList, status);


    }

}

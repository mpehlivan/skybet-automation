package com.skybet.stepdefinitions;

import com.skybet.utilities.ConfigurationReader;
import com.skybet.utilities.Requests;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

public class event_stepdef {

Response response;

    @When("I get all events information")
    public void i_get_all_events_information() {
        response = Requests.getRequest(ConfigurationReader.get("url"));
    }


    @Then("Status code should be {int}")
    public void status_code_should_be(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.statusCode());
    }


    @Then("Verify that all class names are {string}")
    public void verify_that_all_class_names_are(String expectedClassName) {

        Map<String, Object> eventsMap = response.body().as(Map.class);

        List<Map<String,Object>> eventsList = (List<Map<String,Object>>) eventsMap.get("events");

        for (int i=0; i<eventsList.size(); i++){
            Assert.assertEquals(expectedClassName,eventsList.get(i).get("className"));
        }

        System.out.println("Tested events: " + eventsList.size());

    }



    @Then("Verify that there is a ‘home’ and an ‘away’ competitor for each of the events")
    public void verify_that_there_is_a_home_and_an_away_competitor_for_each_of_the_events() {


        Map<String, Object> eventsMap = response.body().as(Map.class);

        List<Map<String,Object>> eventsList = (List<Map<String,Object>>) eventsMap.get("events");


        //assert that there is a HOME competitor for each of the events
        for (int i=0; i<eventsList.size(); i++){

            List<Map<String,Object>> competitorsList = (List<Map<String,Object>>) eventsList.get(i).get("competitors");

            Assert.assertEquals("home",competitorsList.get(0).get("position"));
        }

        //assert that there is an AWAY competitor for each of the events
        for (int i=0; i<eventsList.size(); i++){
            List<Map<String,Object>> competitorsList = (List<Map<String,Object>>) eventsList.get(i).get("competitors");
            Assert.assertEquals("away",competitorsList.get(1).get("position"));
        }


    }




}

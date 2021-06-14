package org.bulatAPI.steps.rebrandly;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.bulatAPI.pojos.rebrandly.RebrandlyLink;
import org.bulatAPI.steps.shared.SharedData;
import org.bulatAPI.utils.ConfigReader;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class RebrandlySteps {
//    private Response response;
    private SharedData sharedData;
    private final static Logger LOGGER = LogManager.getLogger(RebrandlySteps.class);
    private RebrandlyLink actualLink;
    private String linkId;

    public RebrandlySteps(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Before
    public void setUp(){

//        RestAssured.baseURI = "https://api.rebrandly.com";
        LOGGER.debug("Base URL is "+ ConfigReader.getProperty("baseURL_rebrand"));
        RestAssured.baseURI = ConfigReader.getProperty("baseURL_rebrand");

    }

    @When("^all links are requested$")
    public void all_links_are_requested() {
        LOGGER.debug("GET requesting all links, base url "+ConfigReader.getProperty("baseURL_rebrand")+"/v1/links");
        RequestSpecification rs = setHeaders();
        sharedData.response = rs.get("/v1/links");
    }




    @When("^all links are requested with following query params$")
    public void all_links_are_requested_with_following_query_params(List<Map<String,String >> queryParams)  {
        RequestSpecification rs = setHeaders();
        rs.queryParams(queryParams.get(0));

        sharedData.response = rs.get("/v1/links");

    }

    @Then("^only (\\d+) links are returned$")
    public void only_links_are_returned(int expectedNumberOfLinks) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<RebrandlyLink> listOfLinks = Arrays.asList(objectMapper.readValue(sharedData.response.body().asString(),RebrandlyLink[].class));

        MatcherAssert.assertThat(listOfLinks.size(),Matchers.is(expectedNumberOfLinks));

    }


    @When("^link is requested with path param id$")
    public void link_is_requested_with_path_param_id(List<Map<String,String>> paramsData)  {

        RequestSpecification rs = setHeaders();

        sharedData.response = rs.pathParam("id",paramsData.get(0).get("id")).get("/v1/links/{id}");
    }


    @Then("^response id returned is$")
    public void response_id_returned_is(List<Map<String,String>> expectedResponseData) throws JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
       actualLink = objectMapper.readValue(sharedData.response.body().asString(),RebrandlyLink.class);
       MatcherAssert.assertThat(actualLink.getId(),Matchers.is(expectedResponseData.get(0).get("expected_id")));
    }

    @Then("^title name is$")
    public void title_name_is(List<Map<String,String>> expectedDataMap) throws JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
       RebrandlyLink actualLink = objectMapper.readValue(sharedData.response.body().asString(),RebrandlyLink.class);
       MatcherAssert.assertThat(actualLink.getTitle(),Matchers.is(expectedDataMap.get(0).get("title")));
    }

    @When("^new link is created with following fields$")
    public void new_link_is_created_with_following_fields(List<RebrandlyLink> linkData) throws JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
        RequestSpecification rs = setHeaders();

        sharedData.response = rs.body(linkData.get(0)).post("/v1/links");
        actualLink = objectMapper.readValue(sharedData.response.body().asString(),RebrandlyLink.class);
        linkId = actualLink.getId();

    }

    @Then("^response data equals to following$")
    public void response_data_equals_to_following(List<RebrandlyLink> expectedLinkMapData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        actualLink = objectMapper.readValue(sharedData.response.body().asString(),RebrandlyLink.class);
        MatcherAssert.assertThat(actualLink.getTitle(),Matchers.equalTo(expectedLinkMapData.get(0).getTitle()));
        MatcherAssert.assertThat(actualLink.getStatus(),Matchers.is(expectedLinkMapData.get(0).getStatus()));
        MatcherAssert.assertThat(actualLink.getDestination(),Matchers.equalTo(expectedLinkMapData.get(0).getDestination()));
        MatcherAssert.assertThat(actualLink.isPublic(), Matchers.is(expectedLinkMapData.get(0).isPublic()));

    }

    @When("^link is requested to be deleted$")
    public void link_is_deleted()  {
        RequestSpecification rs = setHeaders();
        sharedData.response = rs.pathParam("id",linkId).delete("/v1/links{id}");

    }


    private static RequestSpecification setHeaders() {
        RequestSpecification rs = RestAssured.given();
        rs.headers("apikey", "b6d12716f5ac4af881a0b07219e6807b");
        rs.contentType(ContentType.JSON);
        rs.accept(ContentType.JSON);
        return rs;
    }

}

package tests.api.get;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.Test;
import tests.TestConfig;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestPositiveGetHouses extends TestConfig {

    /**
     * Scenario: Check GET /houses endpoint for New Yourk city
     *
     * Test steps:
     *
     * 1. Get all houses from desired city
     * 2. Assert status code is 200
     * 3. Assert number of houses in desired city is 2
     * 4. Assert that house city has desired value
     *
     * */
    @Test
    public void testGetHousesFromCity(){
        String houseName = "New York";
        int houseCount = 2;

        RequestSpecBuilder builder = new RequestSpecBuilder();
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        builder.addQueryParam("city", houseName);

        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
        // 1. Get all houses from desired city
        var getResponse  = request.get(houses);

        //2. Assert status code is 200
        assertThat(getResponse.statusCode(), is(200));
        ArrayList parsedResponse = getResponse.body().jsonPath().get("city");
        //3. Assert number of houses in desired city is 2
        //4. Assert that house city has desired value
        assertThat(parsedResponse.size(), is(houseCount));
        assertThat(parsedResponse.get(0), is(houseName));

    }

}



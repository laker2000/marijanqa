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
     * Scenario: Check GET /houses endpoint for New York city
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
        String houseCity = "New York";
        int houseCount = 2;

        RequestSpecBuilder builder = new RequestSpecBuilder();
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        builder.addQueryParam("city", houseCity);

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
        assertThat(parsedResponse.get(0), is(houseCity));

    }


    /**
     * Scenario: Check GET /houses endpoint Price upper limit
     *
     * Test steps:
     *
     * 1. Get all houses with price lower or equal to referent one
     * 2. Assert status code is 200
     * 3. Assert that returned house has referent price value for a price
     * 4. Assert house id
     * 5. Assert number of houses with price lower or equal to referent price
     *
     * */
    @Test
    public void testGetHousesPriceLowerThan(){
        int refPrice = 185000;
        int houseCount = 1;
        String houseId = "7aae1963-8f80-4e65-a402-00474ec453a5";

        RequestSpecBuilder builder = new RequestSpecBuilder();
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        builder.addQueryParam("price_lte", refPrice);

        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
        // 1. Get all houses with price lower or equal to referent one
        var getResponse  = request.get(houses);

        //2. Assert status code is 200
        assertThat(getResponse.statusCode(), is(200));
        ArrayList parsedResponse = getResponse.body().jsonPath().get();

        //3. Assert that returned house has referent price value for a price
        assertThat(((HashMap) parsedResponse.get(0)).get("price"), is(refPrice));
        //4. Assert house id
        assertThat(((HashMap) parsedResponse.get(0)).get("id"), is(houseId));
        //5. Assert number of houses with price lower or equal to referent price
        assertThat(parsedResponse.size(), is(houseCount));


    }


    /**
     * Scenario: Check GET /houses endpoint Price lower limit
     *
     * Test steps:
     *
     * 1. Get all houses with price greater or equal to referent one
     * 2. Assert status code is 200
     * 3. Assert that returned house has referent price value for a price
     * 4. Assert house id
     * 5. Assert number of houses with price greater or equal to referent price
     *
     * */
    @Test
    public void testGetHousesPriceGreaterThan(){
        int refPrice = 1150000;
        int houseCount = 1;
        String houseId = "9775dca9-37ba-4cdd-b3f8-507bfea0ab22";

        RequestSpecBuilder builder = new RequestSpecBuilder();
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        builder.addQueryParam("price_gte", refPrice);

        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
        // 1. Get all houses with price greater or equal to referent one
        var getResponse  = request.get(houses);

        //2. Assert status code is 200
        assertThat(getResponse.statusCode(), is(200));
        ArrayList parsedResponse = getResponse.body().jsonPath().get();

        //3. Assert that returned house has referent price value for a price
        assertThat(((HashMap) parsedResponse.get(0)).get("price"), is(refPrice));
        //4. Assert house id
        assertThat(((HashMap) parsedResponse.get(0)).get("id"), is(houseId));
        //5. Assert number of houses with price greater or equal to referent price
        assertThat(parsedResponse.size(), is(houseCount));


    }


    /**
     * Scenario: Check GET /houses endpoint for defined price range
     *
     * Test steps:
     *
     * 1. Get all houses with price between defined range
     * 2. Assert status code is 200
     * 3. Assert number of houses for given range
     *
     * */
    @Test
    public void testGetHousesInRange(){
        int refMinPrice = 450000;
        int refMaxPrice = 660000;
        int houseCount = 4;

        RequestSpecBuilder builder = new RequestSpecBuilder();
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        builder.addQueryParam("price_gte", refMinPrice);
        builder.addQueryParam("price_lte", refMaxPrice);

        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
        // 1. Get all houses with price greater or equal to referent one
        var getResponse  = request.get(houses);

        //2. Assert status code is 200
        assertThat(getResponse.statusCode(), is(200));
        ArrayList parsedResponse = getResponse.body().jsonPath().get();

        //3. Assert number of houses for given range
        assertThat(parsedResponse.size(), is(houseCount));


    }


    /**
     * Scenario: Check GET /houses endpoint for desired city and defined price range
     *
     * Test steps:
     *
     * 1. Get all houses with price between defined range for defined city
     * 2. Assert status code is 200
     * 3. Assert number of houses for given range
     *
     * */
    @Test
    public void testGetHousesInRangeByCity(){
        int refMinPrice = 450000;
        int refMaxPrice = 660000;
        int houseCount = 2;
        String houseCity = "Austin";

        RequestSpecBuilder builder = new RequestSpecBuilder();
        var requestSpec = builder.build();
        var request = RestAssured.given().spec(requestSpec);
        builder.addQueryParam("price_gte", refMinPrice);
        builder.addQueryParam("price_lte", refMaxPrice);
        builder.addQueryParam("city", houseCity);

        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
        // 1. Get all houses with price greater or equal to referent one
        var getResponse  = request.get(houses);

        //2. Assert status code is 200
        assertThat(getResponse.statusCode(), is(200));
        ArrayList parsedResponse = getResponse.body().jsonPath().get();

        //3. Assert number of houses for given range
        assertThat(parsedResponse.size(), is(houseCount));


    }

}



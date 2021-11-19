package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TestConfig {


    protected static String houses;
    protected static String test_config_file;


    @BeforeTest
    public void setup() throws IOException {
        houses = "/houses";
        test_config_file = "./test.config";
        set_env_config(test_config_file);

    }

    private void set_env_config(String test_config) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(test_config));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String curLine,  baseUri = null;
        String[] split;

        while ((curLine = bufferedReader.readLine()) != null){

            if (curLine.contains("  Home")){
                baseUri = bufferedReader.readLine().trim();

            }

        }
        bufferedReader.close();

        if (baseUri == null) {
            throw new IOException("Failed to get environment configuration.");
        }

        RestAssured.baseURI = baseUri;
    }

    //Note: Use in next iteration, when removing code duplication
//    public RequestSpecBuilder prepareRequestBuilder(String basketName) {
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.addPathParam("name", basketName);
//        builder.addHeader("Authorization", serviceToken);
//        return builder;
//    }

}

package com.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tests.support.TestBase;

import io.restassured.RestAssured;
import io.restassured.internal.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPITest extends TestBase
{

    String baseUrl;
    String roomid;
    static int bookingID;
    List<Integer> bookingIdList = new ArrayList<Integer>();

    public GETAPITest()
    {
        super();
    }

    @BeforeMethod
    public void setUp() throws IOException
    {
        logger.info("reading config properties file");
        baseUrl = prop.getProperty("BASEURI");
        roomid = prop.getProperty("roomid");
    }

    @Test(description = "Test that at least 2 existing bookings are returned in the response.", priority = 101)
    public void getBookings()
    {
        
        RestAssured.baseURI = baseUrl;
        logger.info("Base URI is: " + baseUrl);
        
        //Given
        logger.info("Setting Header and Query Params for getBookings() Method");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.queryParam("roomid", roomid);
        
        //When
        logger.info("Calling GET Method");
        Response response = httpRequest.when().get();
        int responseStatusCode = response.getStatusCode();
        String responseString = response.asString();
        JsonPath js = new JsonPath(responseString);
        List ja = js.getList("bookings");
        int numberOfBookings = ja.size();
        
        //Then
        logger.info("Validating GET Test Case");
        Assert.assertEquals(numberOfBookings, 2);
        logger.info("Number of Booking Today is:  " + numberOfBookings);
        Assert.assertEquals(responseStatusCode, HTTP_STATUS_CODE_OK);

        // Fetching all the booking id to validate in the next @Test
        logger.info("Fetching all the booking id to validate in the next @Test");
        for (int i = 0; i < numberOfBookings; i++)
        {
            int value = js.getInt("bookings[" + i + "].bookingid");
            bookingIdList.add(value);
        }

    }

    @Test(description = "Test that the data returned for an existing booking matches.", priority = 102)
    public void getBooking()
    {
        RestAssured.baseURI = baseUrl;
        logger.info("Base URI is: " + baseUrl);
        
        //Given
        logger.info("Setting Header and Query Params for getBooking() Method");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        logger.info("Fetching existing bookings with their booking id");

        for (int count = 0; count < bookingIdList.size(); count++)
        {
            bookingID = bookingIdList.get(count);
            httpRequest.pathParam("id", bookingID);
            
            //When
            logger.info("Calling GET Method");
            Response response = httpRequest.when().get("/{id}");
            int responseStatusCode = response.getStatusCode();
            
            //Then
            logger.info("Validating getBooking() Test Case");
            Assert.assertEquals(responseStatusCode, HTTP_STATUS_CODE_OK);

        }
    }

}
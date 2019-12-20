package com.testcases;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tests.support.PayloadParser;
import com.tests.support.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTAPITest extends TestBase
{
    String baseUrl;

    public POSTAPITest()
    {
        super();
    }

    @BeforeMethod
    public void setUp() throws IOException
    {
        logger.info("reading config properties file");
        baseUrl = prop.getProperty("BASEURI");
    }

    @Test(description = "Test that bookings can be created", priority = 201)
    public void createBooking()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            logger.info("Generating payload from json");
            PayloadParser pp = mapper.readValue(new File("./src/main/resources/com/test/jsonfiles/payload.json"),
                PayloadParser.class);
            String usersJsonString = mapper.writeValueAsString(pp);
            RestAssured.baseURI = baseUrl;
            logger.info("Base URI is" + baseUrl);
            
            //Given
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.header("Content-Type", "application/json");
            httpRequest.body(usersJsonString);
            
            //When
            Response response = httpRequest.when().post();
            int responseStatusCode = response.getStatusCode();
            
            //Then
            logger.info("Validating createBooking() Test Case");
            if (responseStatusCode == HTTP_STATUS_CODE_CONFLICT)
            {
                logger.info("There is a conflict, please recheck the payload");
            }
            else
            {
                Assert.assertEquals(responseStatusCode, HTTP_STATUS_CODE_CREATED);
            }

        }
        catch (JsonParseException e)
        {
            logger.info("invalid json");
        }
        catch (JsonMappingException e)
        {
            logger.info("Incorrect Mapping in Parser class");
        }
        catch (IOException e)
        {
            logger.info("No Json file to read");
        }

    }
}

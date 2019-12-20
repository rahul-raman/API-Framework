package com.tests.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestBase
{

    public int HTTP_STATUS_CODE_OK = 200;
    public int HTTP_STATUS_CODE_UNAUTHORIZED = 401;
    public int HTTP_STATUS_CODE_CREATED = 201;
    public int HTTP_STATUS_CODE_CONFLICT = 409;
    public int HTTP_STATUS_CODE_RESOURCE_NOT_FOUND = 404;

    protected final static Logger logger = Logger.getLogger(TestBase.class);

    public Properties prop;

    public TestBase()
    {
        try
        {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/config.Properties");
            prop.load(ip);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}

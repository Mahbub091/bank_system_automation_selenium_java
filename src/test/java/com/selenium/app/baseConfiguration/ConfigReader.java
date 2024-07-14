package com.selenium.app.baseConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {
    Properties properties;
    String baseDir = System.getProperty("user.dir");
    String path = baseDir + "/src/test/java/com/selenium/app/baseConfiguration/config.properties";
    public ConfigReader(){
        properties = new Properties();
        try{
            FileInputStream fis = new FileInputStream(path);
            try{
                properties.load(fis);
            }catch (Exception e) {
                e.printStackTrace();}
        } catch ( FileNotFoundException e){
            e.printStackTrace();
        }
    }


    public String getAppURL(){
        String AppUrl = properties.getProperty("appURL");
        if (AppUrl != null)
            return AppUrl;
            else
                throw new RuntimeException("App URL Not Specified In Config File");
    }

    public String customerPageUrl(){
        String cusAppUrl = properties.getProperty("customerPage");
        if (cusAppUrl != null)
            return cusAppUrl;
        else
            throw new RuntimeException("Customer App URL Not Specified In Config File");
    }

    public String customerLoginPageUrl(){
        String cusAppUrl = properties.getProperty("userLoginDashBoard");
        if (cusAppUrl != null)
            return cusAppUrl;
        else
            throw new RuntimeException("Customer App URL Not Specified In Config File");
    }

}

package org.manage.browser;

import org.automation.utls.PropertyManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by shantonu on 4/2/16.
 */
public class BrowserFactory {
    public static final String USERNAME = "shantonu";
    public static final String AUTOMATE_KEY = "yourKey";
    public static final String browserstackURL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    private static WebDriver aBrowser ;
    private String nameOfBrowser2 = "ie";
    private String nameOfBrowser1 ;
    private static String nameOfBrowser =  getTheProperties();
    private static DesiredCapabilities capabilities = null;

    /*
    public BrowserFactory(String nameOfBro){
        this.nameOfBrowser1 = nameOfBro;
    }
    */

    public static WebDriver getABrowser(){

        if(nameOfBrowser=="firefox"){
            aBrowser = new FirefoxDriver();
        }
        else if(nameOfBrowser=="edge"){
            aBrowser = new EdgeDriver();
        }
        else if(nameOfBrowser=="opera"){
            aBrowser = new OperaDriver();
        }
        else if(nameOfBrowser=="ie"){
            File iedriver = new File(GetIePath());
            System.setProperty("webdriver.ie.driver", iedriver.getAbsolutePath());
            //-Dwebdriver.ie.driver=physicall
            aBrowser = new InternetExplorerDriver();
        }
        else if(nameOfBrowser=="chrome"){
            aBrowser = new ChromeDriver();
        }
        else if(nameOfBrowser=="safari"){
            aBrowser = new SafariDriver();
        }
        else if(nameOfBrowser=="Appium"){
            // Todo ->
        }
        else if(nameOfBrowser=="phantomjs"){
            // Todo ->
            //aBrowser = new
            }
        else if(nameOfBrowser=="browserstack"){
            try {
                return new RemoteWebDriver(new URL(browserstackURL), );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(nameOfBrowser=="soucelab"){
            // Todo ->

        }
        else
        {
            // Todo ->
            //aBrowser = new HtmlUnitDriver();
        }

        return aBrowser;
    }

    private static String getTheProperties(){
        try {
            return PropertyManager.getProperty("browser.properties","selenium.browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String GetIePath(){
        try {
            return PropertyManager.getProperty("browser.properties","selenium.browser.ie.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DesiredCapabilities setCapabilitiesForBrowserstack(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser", "Firefox");
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("browser_version", "41.0");
        capabilities.setCapability("os", "linux");
        //capabilities.setCapability("os_version", "7");
        capabilities.setCapability("browserstack.debug", "true");
        return capabilities;
    }
    private  static DesiredCapabilities setCapabilitiesForPhantomJS(){
        capabilities = DesiredCapabilities.phantomjs();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("takesScreenshot", true);
        return capabilities;
    }

    private static DesiredCapabilities getCapabilities(String remoteBrowserName){

        if(remoteBrowserName=="browserstack"){

        }


        return capabilities;

    }
}

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

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by shantonu on 4/2/16.
 */
class BrowserFactory {


    private static WebDriver aBrowser ;
    private String nameOfBrowser2 = "ie";
    private String nameOfBrowser1 ;
    private static String nameOfBrowser =  getTheProperties();
    private static DesiredCapabilities capabilities = null;

    private static String internalRemoteDriverConfig = "remote-firefox";
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
        else if(nameOfBrowser=="appium-ios"){
            try {
                aBrowser = new IOSDriver(new URL(AppiumCapabilities.appiumURL),DesiredcapabilityFactory.getCapability("appium-ios") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(nameOfBrowser=="appium-android"){
            try {
                aBrowser = new AndroidDriver(new URL(AppiumCapabilities.appiumURL),DesiredcapabilityFactory.getCapability("appium-android") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(nameOfBrowser=="browserstack"){
            try {
                return new RemoteWebDriver(new URL(BrowserStackCapabilities.browserstackURL),DesiredcapabilityFactory.getCapability("browserstack") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(nameOfBrowser=="soucelab"){

            try {
                return new RemoteWebDriver(new URL(SauceLabCapabilities.sauceLabURL),DesiredcapabilityFactory.getCapability("saucelabs") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else
        {
            return new RemoteWebDriver(DesiredcapabilityFactory.getCapability(internalRemoteDriverConfig));
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




}

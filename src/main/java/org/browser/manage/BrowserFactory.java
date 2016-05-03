package org.browser.manage;

import automation.utils.PropertyUtil;
import org.browser.manage.remote.capability.DesiredcapabilityFactory;
import org.browser.utils.RemoteConfig;
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

    //todo => desire capability property reading for name of browser
    private static DesiredCapabilities capabilities = null;

    private static String internalRemoteDriverConfig = "node1.firefox.1920x1080";
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

        else if(nameOfBrowser=="browserstack"){
            try {
                // todo , get this capability config from property
                return new RemoteWebDriver(new URL(RemoteConfig.browserstack_URL), DesiredcapabilityFactory.getCapability("browserstack") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(nameOfBrowser=="soucelab"){

            try {
                return new RemoteWebDriver(new URL(RemoteConfig.saucelabs_URL),DesiredcapabilityFactory.getCapability("saucelabs") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(nameOfBrowser=="appium-ios"){
            try {
                aBrowser = new IOSDriver(new URL(RemoteConfig.appium_URL),DesiredcapabilityFactory.getCapability("appium-ios") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(nameOfBrowser=="appium-android"){
            try {
                aBrowser = new AndroidDriver(new URL(RemoteConfig.appium_URL), DesiredcapabilityFactory.getCapability("appium-android") );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else
        {

            try {
                return new RemoteWebDriver(new URL(RemoteConfig.inouse_hubUrl), DesiredcapabilityFactory.getInhouseCapbilities(internalRemoteDriverConfig));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return aBrowser;
    }

    private static String getTheProperties(){
        try {
            return PropertyUtil.getProperty("browser.properties","selenium.browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String GetIePath(){
        try {
            return PropertyUtil.getProperty("browser.properties","selenium.browser.ie.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}

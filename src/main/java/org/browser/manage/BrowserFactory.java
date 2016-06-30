package org.browser.manage;

import automation.utils.PropertyUtil;
import org.browser.utils.RemoteConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
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
public class BrowserFactory {
    private static WebDriver aBrowser;
    private static String nameOfBrowser = getDefaultLocalBrowserName();

    //todo => desire capability property reading for name of browser
    private static DesiredCapabilities capabilities = null;

    private static String internalRemoteDriverConfig = "node1.firefox.1920x1080";


    /***
     * This is responsible for local remote hub initiation, not finalized, separated from default function dule to minimize complexity.
     *
     * @param intranetHub = hostIP:PORT
     * @param browser     = browser name
     * @return TODO , seperate & specify capability.
     */
    public static WebDriver getABrowser(String intranetHub, String browser) {
        try {
            return new RemoteWebDriver(new URL(intranetHub), CapabilityFactory.getCapability(browser));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return aBrowser;
        }

    }

    public static WebDriver getABrowser() {
        return getABrowser(nameOfBrowser);
    }


    // todo this two should be replace by enum
    public static WebDriver getARemoteBrowser(String provider, String browserCapability) {

        if (provider == "browserstack") {
            try {
                // todo , get this capability config from property
                return new RemoteWebDriver(new URL(RemoteConfig.browserstack_URL), CapabilityFactory.getCapability(browserCapability));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (provider == "soucelab") {

            try {
                return new RemoteWebDriver(new URL(RemoteConfig.saucelabs_URL), CapabilityFactory.getCapability(browserCapability));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (provider == "appium-ios") {
            try {
                aBrowser = new IOSDriver(new URL(RemoteConfig.appium_URL), CapabilityFactory.getCapability(browserCapability));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (provider == "appium-android") {
            try {
                aBrowser = new AndroidDriver(new URL(RemoteConfig.appium_URL), CapabilityFactory.getCapability(browserCapability));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return aBrowser;
    }

    public static WebDriver getABrowser(String browserName) {
        String os = PropertyUtil.getSystemProperty("os.name");
        if ("firefox".equals(browserName)) {
            if (os.contains("windows")) {
                PropertyUtil.setSystemProperty("webdriver.firefox.bin", "place where you unzipped firefox executable");
            } else {
                PropertyUtil.setSystemProperty("webdriver.firefox.bin", "/home/shantonu/ff46/firefox");
            }
            aBrowser = new FirefoxDriver();
        } else if ("edge".equals(browserName)) {
            aBrowser = new EdgeDriver();
        } else if ("opera".equals(browserName)) {
            aBrowser = new OperaDriver();
        } else if ("ie".equals(browserName)) {
            //File iedriver = new File(getIEPath()); // reading from property //-Dwebdriver.ie.driver=physicall using command line
            File iedriver = new File("Point your Selenium Server exe Path");//todo for your PC
            PropertyUtil.setSystemProperty("webdriver.ie.driver", iedriver.getAbsolutePath());

            aBrowser = new InternetExplorerDriver();
        } else if ("chrome".equals(browserName)) {

            String pathWindows = "C:\\Users\\%USERNAME%\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";// can be changed for your PC
            String pathlunix = "/usr/local/bin/chromedriver";
            ChromeDriverService service;
            if (os.contains("windows")) {
                PropertyUtil.setSystemProperty("webdriver.chrome.driver", pathWindows);
                service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(pathlunix))
                        .usingAnyFreePort()
                        .build();
            } else {
                PropertyUtil.setSystemProperty("webdriver.chrome.driver", pathlunix);
                service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(pathlunix))
                        .usingAnyFreePort()
                        .build();
            }
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());

        } else if ("safari".equals(browserName)) {
            aBrowser = new SafariDriver();
        } else {
            return getDefaultDriver();
        }

        return aBrowser;
    }

    public static WebDriver getDefaultRemoteDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL(RemoteConfig.inhouse_hubUrl), CapabilityFactory.getDefault());
    }

    public static WebDriver getDefaultDriver() {
        return getABrowser("chrome");
    }

    private static String getDefaultLocalBrowserName() {
        try {
            return PropertyUtil.getProperty("browser.properties", "selenium.browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getIEPath() {
        try {
            return PropertyUtil.getProperty("browser.properties", "selenium.browser.ie.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

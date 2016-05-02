package org.browser.manage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shantonu on 5/2/16.
 * This will maintain all inhouse Selenium Remote Driver setup for grid
 */
class InHouseSeGridDriver {
    //todo, get all of this from property
    public static String hubIp="172.16.170.138";
    public static String hubport="4444";
    public static String hubUrl = "http://"+hubIp+":"+hubport+"/wd/hub";

    public static WebDriver getDriver(DesiredCapabilities capabilities) throws MalformedURLException {
        return new RemoteWebDriver(new URL(hubUrl), capabilities);
    }

}

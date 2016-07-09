package org.automation.browser.manage.local;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by shantonu on 4/29/16.
 * See firefox manager, same thing for chrome
 * TOdo apply like as firefox manager
 *all possible combination of
 */

public class ChromeManager {
    private ChromeOptions options;
    private WebDriver driver;
    private String pathToChrome;

    public ChromeOptions enableChromePlugins(String pathToCRX) throws IOException {
        File extention = new File(pathToCRX);
        options.addExtensions(extention);
        return options;
    }

    public WebDriver getDriverWithOption(ChromeOptions options){
        driver = new ChromeDriver(options);
        return driver;
    }
    public WebDriver getDriverWithExtention(String pathToCRX) throws IOException {
        return getDriverWithOption(enableChromePlugins(pathToCRX));
    }

    public WebDriver getRemoteDriverWithOptionAndCapabilities(ChromeOptions options, DesiredCapabilities capabilities){
        DesiredCapabilities cap;
        ChromeOptions op;
        if(null==capabilities){
            cap = DesiredCapabilities.chrome();
        }else
            cap=capabilities;
        if(null!=options) {
            cap.setCapability(ChromeOptions.CAPABILITY, options);
        }
        driver = new RemoteWebDriver(cap);
        return driver;
    }

}

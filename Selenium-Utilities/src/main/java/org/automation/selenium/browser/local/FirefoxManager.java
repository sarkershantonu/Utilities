package org.automation.selenium.browser.local;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by shantonu on 4/7/16.
 * this is spatial class to maintain
 * Custom firefox drivers (local + remote)
 * security feature support for secure application
 * Initial plan for using firefox only
 * todo =>
 * 1.  Firefox profile management
 * 2. Common Session management & protection
 * Example : http://www.stubhub.com/, try recording with selenium, it will prompt for sensing JS activity by selenium
 */
public class FirefoxManager {
    private FirefoxProfile profile;
    private WebDriver driver;
    private String pathTofirefox;

    public String getFirefoxPath(){return pathTofirefox;}
    public void setFirefoxPath(String path){
        this.pathTofirefox = path;
        System.setProperty("webdriver.firefox.bin", pathTofirefox);
    }

    public FirefoxManager(){

    }
    public FirefoxProfile enableFirefoxPlugins(String pathToXPI) throws IOException {
        profile = new FirefoxProfile();
        File extention = new File(pathToXPI);
        profile.addExtension(extention);
        return profile;
    }

    public WebDriver getFirefoxWithFirebug(String pathTofirebugXPI, String firebugVersion){
        profile = new FirefoxProfile();
        try {
            profile.addExtension(new File(pathTofirebugXPI));
        } catch (IOException e) {
            e.printStackTrace();
        }
        profile.setPreference("extensions.firebug.currentVersion", firebugVersion);
        driver = new FirefoxDriver(profile);
        return driver;

    }
    public WebDriver getFirefoxWithProfile(String nameOfProfile){
        driver = new FirefoxDriver(getProfile(nameOfProfile));
        return  driver ;
    }
    public WebDriver getFirefoxWithProfile(FirefoxProfile nameOfProfile){
        driver = new FirefoxDriver(nameOfProfile);
        return  driver ;
    }

    public WebDriver getFirfoxWithProfileAndPlugins(String nameOfProfile, String pathToXPI) throws IOException {
        FirefoxProfile pro = enableFirefoxPlugins(pathToXPI);
        return  getFirefoxWithProfile(pro) ;
    }

    public FirefoxProfile getProfile(String nameOfProfile){
        ProfilesIni profileIni=  new ProfilesIni();
        if(null!=nameOfProfile){
            profile = profileIni.getProfile(nameOfProfile);
        }
        else
            profile =profileIni.getProfile( fetDefaultProfileName());

        return  profile;
    }

    public WebDriver getRemoteFirefoxWithCapabilities(DesiredCapabilities capabilities){
        driver = new RemoteWebDriver(capabilities);
        return driver;

    }
    public WebDriver getRemoteFirefoxWithProfileAndCapabilities(DesiredCapabilities capabilities, String profileName){
        profile = getProfile(profileName); //default profile loading
        capabilities.setCapability(FirefoxDriver.PROFILE,profile);
        driver = new RemoteWebDriver(capabilities);
        return driver;
    }

    public WebDriver getRemoteFirefoxWithCapabilities(){
        driver = new RemoteWebDriver(DesiredCapabilities.firefox());
        return driver;

    }
    public WebDriver getRemoteFirefoxWithProfileAndCapabilities(){
        profile = getProfile(null); //default profile loading
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        cap.setCapability(FirefoxDriver.PROFILE,profile);
        driver = new RemoteWebDriver(cap);
        return driver;
    }
    private String fetDefaultProfileName() {
        return null;// todo from properties.
    }

}

package org.browser.utils;

/**
 * Created by shantonu on 5/3/16.
 * This is responsible to contain all access config of remote environments
 */
public class RemoteConfig {
    public static final String appium_USERNAME = "shantonu";
    public static final String appium_ACCESS_KEY = "yourKey";
    // todo -> make this reading from properties
    public static final String appium_URL = "http://" + appium_USERNAME + ":" + appium_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

    private static final String browserstack_USERNAME = "shantonu";
    private static final String browserstack_AUTOMATE_KEY = "yourKey";
    // todo -> make this reading from properties
    public static final String browserstack_URL = "http://" + browserstack_USERNAME + ":" + browserstack_AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    private static final String saucelabs_USERNAME = "shantonu";
    private static final String saucelabs_ACCESS_KEY = "yourKey";
    // todo -> make this reading from properties
    public static final String saucelabs_URL = "http://" + saucelabs_USERNAME + ":" + saucelabs_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

    public static String inouse_hubIp="172.16.170.138";
    public static String inouse_hubport="4444";
    public static String inouse_hubUrl = "http://"+inouse_hubIp+":"+inouse_hubport+"/wd/hub";


}

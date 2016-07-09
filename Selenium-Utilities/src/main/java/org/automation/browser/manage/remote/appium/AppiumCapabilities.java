package org.automation.browser.manage.remote.appium;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by shantonu on 4/7/16.
 *
 * As this is part of soucelab, see SauceLabCapabilities comments to get details
 */
public class AppiumCapabilities {

    /**
     * souce lab capabilities  https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
     * add as much as you can to add
     * @param capabilities
     * @param config format => osName.device.appiumVersion.moce(hybrid/app/web)
     *               No dot for versions, dot reserverd as seperator
     *todo => need to check all items and decide this
     *
     * @return => returned what changed.
     * Sample 2 configuration given
     *todo => Add as much as you can
     *full => https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options
     */
    public  static DesiredCapabilities getCapabilities(DesiredCapabilities capabilities, String config){

        if(config=="and41.htc1x.appium151.hybrid") {
            capabilities = DesiredCapabilities.android();
            capabilities.setCapability("appiumVersion", "1.5.1");
            capabilities.setCapability("deviceName","HTC One X Emulator");
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("browserName", "");
            capabilities.setCapability("platformVersion","4.1");
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("automationName","Selendroid");
        }

        else if(config=="ops92.ipadretina.appium151.app"){
            capabilities = DesiredCapabilities.iphone();
            capabilities.setCapability("appiumVersion", "1.5.1");
            capabilities.setCapability("deviceName","iPad Simulator");
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("platformVersion","9.2");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("app","sauce-storage:www.google.com");//www.google.com is app url
        }

        // // TODO: add more
        return capabilities;
    }

    /**
     * to minimize duplication of capability setting, use commonly used capbility settings for different divices
     * @param capabilities
     * @param deviceType => pc/mac browser, iphone-android browser, tab browser , will increase based on compatibility
     * @return
     * todo => as as much as you can , see settings in https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/ and add common configs
     */
    private static DesiredCapabilities setCommon(DesiredCapabilities capabilities, String deviceType){

        if(deviceType=="mobile"){
            capabilities.setCapability("deviceOrientation", "portrait");
        }
        else if(deviceType=="tab") {

        }
        else if (deviceType=="android"){

        }
        else if (deviceType=="iphone"){

        }
        else if (deviceType=="ipad"){

        }

        return capabilities;
    }
}

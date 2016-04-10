package org.browser.manage;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by shantonu on 4/7/16.
 * this class is responsible for all browser stack configuration based capabilities
 */
class BrowserStackCapabilities {

    private static final String USERNAME = "shantonu";
    private static final String AUTOMATE_KEY = "yourKey";
    // todo -> make this reading from properties
    public static final String browserstackURL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
    

    /**
     * @param capabilities => will get to set the value
     * @param config format => osName.browsername.resolution
     *               No dot for versions, dot reserverd as seperator
     *               COnfig sample => https://www.browserstack.com/automate/java
     * @return => returned what changed.
     * Sample 3 configuration given
     * todo => add as much capability as you can from https://www.browserstack.com/start
     */
    static DesiredCapabilities getCapabilities(DesiredCapabilities capabilities, String config){

        if(config=="win7.ie8.1024x768") {
            capabilities.setCapability("browser", "IE");
            capabilities.setCapability("browser_version", "8.0");
            capabilities.setCapability("os", "Windows");
            capabilities.setCapability("os_version", "7");
            capabilities.setCapability("resolution", "1024x768");
            setCommon(capabilities, "pc");
        }

        else if(config=="win7.ff41.1024x768"){
            capabilities.setCapability("browser", "Firefox");
            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability("browser_version", "41.0");
            capabilities.setCapability("os", "Windows");
            capabilities.setCapability("os_version", "7");
            setCommon(capabilities, "pc");
        }
        else if(config=="iphone6.iphone.mobile"){
            capabilities.setCapability("browserName", "iPhone");
            capabilities.setPlatform(Platform.MAC);
            capabilities.setCapability("device", "iPhone 6");
            setCommon(capabilities, "iphone");
        }
        // // TODO: add more
        return capabilities;
    }

    /**
     * to minimize duplication of capability setting, use commonly used capbility settings for different divices
     * @param capabilities
     * @param deviceType => pc/mac browser, iphone-android browser, tab browser , will increase based on compatibility
     * @return
     *
     *
     */
    private static DesiredCapabilities setCommon(DesiredCapabilities capabilities, String deviceType){
        if(deviceType=="mobile"){

        }
        else if (deviceType=="pc"){
            capabilities.setCapability("browserstack.debug", "true");

        }else if(deviceType=="tab"){

        }else if (deviceType=="mac"){

        }
        else if (deviceType=="android"){

        }
        else if (deviceType=="iphone"){

        } else if (deviceType=="ipad"){

        }
        return capabilities;
    }
}

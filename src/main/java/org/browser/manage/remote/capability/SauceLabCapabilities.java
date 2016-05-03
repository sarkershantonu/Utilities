package org.browser.manage.remote.capability;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by shantonu on 4/7/16.
 * this class is responsible for all browser stack configuration based capabilities
 * main link=> https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
 *
 */
public class SauceLabCapabilities {



    /**
     * souce lab capabilities  https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
     * add as much as you can to add
     * @param capabilities
     * @param config format => osName.browsername.resolution
     *               No dot for versions, dot reserverd as seperator
     *
     * @return => returned what changed.
     * Sample 3 configuration given
     *todo => Add as much as you can
     */
    static DesiredCapabilities getCapabilities(DesiredCapabilities capabilities, String config){

        if(config=="win7.ie8.1024x768") {
            capabilities =  DesiredCapabilities.internetExplorer();
            capabilities.setCapability("platform", "Windows 7");
            capabilities.setCapability("version", "8.0");
            setCommon(capabilities, "pc");
        }

        else if(config=="win7.ff41.1024x768"){
            capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("version", "45.0");
            capabilities.setCapability("platform", "window 7");

            setCommon(capabilities, "pc");
        }
        else if(config=="iphone6.iphone.mobile"){
            capabilities = DesiredCapabilities.iphone();
            capabilities.setCapability("platform", "OS X 10.10");
            capabilities.setCapability("version", "9.2");
            capabilities.setCapability("deviceName","iPhone 6");
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
     * todo => as as much as you can , see settings in https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/ and add common configs
     */
    private static DesiredCapabilities setCommon(DesiredCapabilities capabilities, String deviceType){
        String resulation = "1024x768";

        if(deviceType=="mobile"){
            capabilities.setCapability("deviceOrientation", "portrait");
        }
        else if (deviceType=="pc"){
            capabilities.setCapability("screenResolution", resulation);

        }else if(deviceType=="tab"){

        }else if (deviceType=="mac"){

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

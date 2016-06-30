package org.browser.manage.remote;

import org.browser.manage.remote.appium.AppiumCapabilities;
import org.browser.manage.remote.browserstack.BrowserStackCapabilities;
import org.browser.manage.remote.saucelab.SauceLabCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by shantonu on 4/6/16.
 * This class is responsible for getting all capabilities(local + remote)
 * TODO => get all compatibility configuration from property file
 */
public class DesiredcapabilityFactory {

    private static DesiredCapabilities capabilities;
    private DesiredcapabilityFactory(){}

    /**
     *
     * @param nameOfBrowser
     * @return
     */
    public static DesiredCapabilities getCapability(String nameOfBrowser){
        capabilities = new DesiredCapabilities();

        if(nameOfBrowser=="browserstack"){
            BrowserStackCapabilities.getCapabilities(capabilities,"win7.ff41.1024x768");
        }
        else if(nameOfBrowser=="soucelab"){
            SauceLabCapabilities.getCapabilities(capabilities,"iphone6.iphone.mobile");
        }
        else if(nameOfBrowser=="appium-android"){
            AppiumCapabilities.getCapabilities(capabilities,"and41.htc1x.appium151.hybrid");
        }
        else if(nameOfBrowser=="appium-ios"){
            AppiumCapabilities.getCapabilities(capabilities,"ops92.ipadretina.appium151.app");
        }
        else if(nameOfBrowser=="phantomjs"){
            capabilities = DesiredCapabilities.phantomjs();
            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability("takesScreenshot", true);
        }
        else if(nameOfBrowser=="remote-android"){
            capabilities = DesiredCapabilities.android();
        }
        else if(nameOfBrowser=="remote-chrome"){
            capabilities = DesiredCapabilities.chrome();
        }
        else if(nameOfBrowser=="remote-edge"){
            capabilities = DesiredCapabilities.edge();
        }
        else if(nameOfBrowser=="remote-firefox"){
            capabilities = DesiredCapabilities.firefox();

        }
        else if(nameOfBrowser=="remote-htmlunit"){
            capabilities = DesiredCapabilities.htmlUnit();
        }
        else if(nameOfBrowser=="remote-htmljs"){
            capabilities = DesiredCapabilities.htmlUnitWithJs();
        }
        else if(nameOfBrowser=="remote-ie"){
            capabilities = DesiredCapabilities.internetExplorer();
        }
        else if(nameOfBrowser=="remote-ipad"){
            capabilities = DesiredCapabilities.ipad();
        }
        else if(nameOfBrowser=="remote-iphone"){
            capabilities = DesiredCapabilities.iphone();
        }
        else if(nameOfBrowser=="remote-opera"){
            capabilities = DesiredCapabilities.operaBlink();

        }
        else if(nameOfBrowser=="remote-phantomjs"){
            capabilities = DesiredCapabilities.phantomjs();
        }
        else if(nameOfBrowser=="remote-safari") {
            capabilities = DesiredCapabilities.safari();
        }
        else{
            //todo default common capabilities will be here
        }


        return capabilities;
    }

    /**
     * Using firefox manager, this will manage secure site capabilities, supporting managed cache, cookie, custom herder etc
     * @return
     */
    public static DesiredCapabilities getCapabilityWithSecurity(){

        return capabilities;
    }

    /**
     * Big todo for inhouse capability entries
     * will be reading from property , and node wise config
     * @return
     */
    public static DesiredCapabilities getInhouseCapabilities(String config){
        return capabilities;
    }


}

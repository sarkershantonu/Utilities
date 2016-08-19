package org.automation.selenium.proxy;


import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by shantonu on 4/29/16.
 */
public class ProxyUtils {

    private static String proxyHost = "localhost";
    private static String proxyport = "8080";
    private static String proxyurl = proxyHost+":"+ proxyport;
    private static Proxy proxy;

    /**
     * Todo, read from properties
     */
    private static void getProxySettingsFromProperty(){

        proxyHost="";
        proxyport="";
    }

    public static DesiredCapabilities setProxyCapability(DesiredCapabilities capabilities){
        proxy = new Proxy();
        proxy.setHttpProxy(proxyurl).setFtpProxy(proxyurl).setSslProxy(proxyurl);
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        return capabilities;
    }
    public static DesiredCapabilities getDedaultProxyCapability(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        proxy = new Proxy();
        proxy.setHttpProxy(proxyurl).setFtpProxy(proxyurl).setSslProxy(proxyurl);
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        return capabilities;
    }

}

package org.browser.manage.remote;

import org.browser.manage.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Semaphore;

/**
 * Created by shantonu on 7/1/16.
 *
 * todo , implement a managable parallal system driven by remote drivers (local support also)
 */
public class SeleniumHub {
    private Semaphore available = new Semaphore(1, true);
    private static int counter = 0;
    private int timeout;

    private Queue<WebDriver> drivers = new LinkedList<>();
    private final LinkedList<RemoteWDConnector> remoteWDConnectors = new LinkedList<>();
    private BlockingDeque<RemoteWebDriver> remoteWebDrivers;

    private final boolean isReeusable;
    private final boolean isRemote ;

    public SeleniumHub(Queue<WebDriver> drivers,boolean isReeusable ,boolean isRemote, int timeout, int ready) {
        this.drivers = drivers;
        this.isReeusable = isReeusable;
        this.isRemote = isRemote;
        this.timeout = timeout;


    }

    public WebDriver init(){
        try {
            return BrowserFactory.getDefaultRemoteDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void ready(int drivers){
        synchronized (this){
            this.available.release(drivers);
        }
    }
    public WebDriver getDriver(){
        try{
            this.available.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriver driver  = null;
        if(isReeusable){
            driver =this.drivers.poll();
        }
        if (driver.equals(null)){
            driver = init();
        }
        return driver;
    }
}

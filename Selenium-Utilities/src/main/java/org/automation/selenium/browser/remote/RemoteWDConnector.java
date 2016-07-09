package org.automation.selenium.browser.remote;

import org.automation.selenium.browser.BrowserFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.BlockingDeque;

/**
 * Created by shantonu on 7/1/16.
 */
public class RemoteWDConnector extends Thread{
    private BlockingDeque<RemoteWebDriver> drivers;
    private String url;

    public RemoteWDConnector(String url, final BlockingDeque<RemoteWebDriver> allRemotedrivers){
        this.drivers=allRemotedrivers;
        this.url=url;
    }
    @Override
    public void run(){

        try {
            RemoteWebDriver driver =  BrowserFactory.getDefaultRemoteDriver();
            drivers.put(driver);
        } catch (InterruptedException e) {
            this.interrupt();
        }catch (Exception e){
            e.printStackTrace();
            this.hold();
        }
    }

    public final void hold(){
        try {
            this.sleep(2000);//default waut
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

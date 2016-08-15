package org.automation.selenium.page;

import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/10/16.
 */
public class NavigationSeleniumUtil extends SeleniumUtilBase {

    public NavigationSeleniumUtil(WebDriver aDriver){
        super(aDriver);
    }
    public void goBack(){
        driver.navigate().back();
    }
    public void goForward(){
        driver.navigate().forward();
    }
    public void refresh(){
        driver.navigate().refresh();
    }
    private void makeFullScreen() {
        driver.manage().window().maximize();
    }

    public void asdasd(){

    }


}

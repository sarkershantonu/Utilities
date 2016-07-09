package org.automation.selenium.page;

import org.automation.selenium.UtilBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/10/16.
 */
public class NavigationUtil extends UtilBase {

    public NavigationUtil(WebDriver aDriver){
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

package project.selenium.page.utils;

import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/10/16.
 */
public class UtilBase {
    protected WebDriver driver = null;

    public UtilBase(WebDriver aDriver){
        this.driver = aDriver;
    }
}
  
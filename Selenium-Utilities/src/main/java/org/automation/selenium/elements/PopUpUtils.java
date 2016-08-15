package org.automation.selenium.elements;


import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/29/16.
 * This class will be responsible for handling all popup isseues
 * so, switching from frame to frame
 * moving different handles are included
 * This is not page specific, it will support different layers. too
 */
public class PopUpUtils extends SeleniumUtilBase {
    public PopUpUtils(WebDriver aDriver) {
        super(aDriver);
    }

}

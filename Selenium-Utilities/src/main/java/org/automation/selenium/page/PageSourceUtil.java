package org.automation.selenium.page;

import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;


/**
 * Created by shantonu on 4/19/16.
 */
public class PageSourceUtil extends SeleniumUtilBase {



    public PageSourceUtil(WebDriver aDriver) {
        super(aDriver);
    }

    /**
     * This will get all resources (css, image, font, javascript etc from driver source
     * @param resourceName
     * @return
     * todo
     */
    public InputStream getResourceAsStream(String resourceName){
        return null;
    }

}

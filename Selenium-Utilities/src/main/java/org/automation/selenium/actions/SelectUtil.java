package org.automation.selenium.actions;

import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by shantonu on 8/24/16.
 */
public class SelectUtil extends SeleniumUtilBase {
    private Select select;

    public SelectUtil(WebDriver aDriver) {
        super(aDriver);
    }
}

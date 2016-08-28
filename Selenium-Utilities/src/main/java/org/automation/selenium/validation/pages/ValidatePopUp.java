package org.automation.selenium.validation.pages;

import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 8/28/16.
 * A pop is like a page (same as iframe) so, each component validation will here based on popup actual item
 */
public abstract class ValidatePopUp extends SeleniumUtilBase{

    public ValidatePopUp(WebDriver aDriver) {
        super(aDriver);

    }

    public abstract void validateAllChildElements();

}

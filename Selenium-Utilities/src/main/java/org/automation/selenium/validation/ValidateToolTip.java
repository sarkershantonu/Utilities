package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateToolTip extends ValidateWebElement {
    public ValidateToolTip(WebElement webElement) {
        super(webElement);
    }

    public ValidateToolTip(By by) {
        super(by);
    }
}

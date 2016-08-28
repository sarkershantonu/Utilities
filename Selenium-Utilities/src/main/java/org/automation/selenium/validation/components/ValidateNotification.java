package org.automation.selenium.validation.components;

import org.automation.selenium.validation.ValidateWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateNotification extends ValidateWebElement {
    public ValidateNotification(WebElement webElement) {
        super(webElement);
    }

    public ValidateNotification(By by) {
        super(by);
    }
}

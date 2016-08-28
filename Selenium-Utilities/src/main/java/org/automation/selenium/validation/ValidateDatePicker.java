package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateDatePicker extends ValidateWebElement {

    public ValidateDatePicker(WebElement webElement) {
        super(webElement);
    }

    public ValidateDatePicker(By by) {
        super(by);
    }
}

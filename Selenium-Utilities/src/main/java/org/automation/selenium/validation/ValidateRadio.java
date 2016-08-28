package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateRadio extends ValidateWebElement {

    public ValidateRadio(WebElement webElement) {
        super(webElement);
    }

    public ValidateRadio(By by) {
        super(by);
    }
}

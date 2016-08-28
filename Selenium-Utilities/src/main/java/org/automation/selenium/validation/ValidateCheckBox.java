package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateCheckBox extends ValidateWebElement {

    public ValidateCheckBox(WebElement webElement) {
        super(webElement);
    }

    public ValidateCheckBox(By by) {
        super(by);
    }
}

package org.automation.selenium.validation.components;

import org.automation.selenium.validation.ValidateWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidatePagination extends ValidateWebElement {
    public ValidatePagination(WebElement webElement) {
        super(webElement);
    }

    public ValidatePagination(By by) {
        super(by);
    }
}

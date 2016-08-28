package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateICON extends ValidateWebElement {
    public ValidateICON(WebElement webElement) {
        super(webElement);
    }

    public ValidateICON(By by) {
        super(by);
    }
}

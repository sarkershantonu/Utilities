package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateIcon extends ValidateWebElement {
    public ValidateIcon(WebElement webElement) {
        super(webElement);
    }

    public ValidateIcon(By by) {
        super(by);
    }
}

package org.automation.selenium.validation.components;

import org.automation.selenium.validation.ValidateWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateButton extends ValidateWebElement {

    public ValidateButton(WebElement webElement) {
        super(webElement);
    }

    public ValidateButton(By by) {
        super(by);
    }

    public void testAButton() throws AssertionError{

    }

}

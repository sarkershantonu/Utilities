package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateTextBox extends ValidateWebElement {

    public ValidateTextBox(WebElement webElement) {
        super(webElement);
    }

    public ValidateTextBox(By by) {
        super(by);
    }

    public void testATextBox() throws AssertionError{

    }
}

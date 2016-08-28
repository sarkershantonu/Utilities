package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateListBox extends ValidateWebElement {

    public ValidateListBox(WebElement webElement) {
        super(webElement);
    }

    public ValidateListBox(By by) {
        super(by);
    }
}

package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateSearchBox extends ValidateTextBox {


    public ValidateSearchBox(WebElement webElement) {
        super(webElement);
    }

    public ValidateSearchBox(By by) {
        super(by);
    }
}

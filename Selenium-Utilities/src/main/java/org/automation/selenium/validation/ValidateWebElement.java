package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 5/19/16.
 * This is responsible for having all test logic for a test element
 * These are common test for static tests
 * Example : CSS value, item property etc.
 * type are chooses from here
 * https://www.usability.gov/how-to-and-tools/methods/user-interface-elements.html
 */
public class ValidateWebElement {
    private WebElement element;
    private By by;

    public ValidateWebElement(WebElement webElement){
        this.element = webElement;
    }
    public ValidateWebElement(By by){
        this.by=by;
    }


}

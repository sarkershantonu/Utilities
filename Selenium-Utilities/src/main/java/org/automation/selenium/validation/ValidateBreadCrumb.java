package org.automation.selenium.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 * Breadcrumbs allow users to identify their current location within the system by providing a clickable trail of proceeding pages to navigate by.
 */
public class ValidateBreadCrumb extends ValidateWebElement {
    public ValidateBreadCrumb(WebElement webElement) {
        super(webElement);
    }

    public ValidateBreadCrumb(By by) {
        super(by);
    }
}

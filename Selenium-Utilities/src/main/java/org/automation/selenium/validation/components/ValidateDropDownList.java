package org.automation.selenium.validation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidateDropDownList extends ValidateListBox {


    public ValidateDropDownList(WebElement webElement) {
        super(webElement);
    }

    public ValidateDropDownList(By by) {
        super(by);
    }

    public void testACombo() throws AssertionError{

    }

    public void testAComboItems() throws AssertionError{

    }
}

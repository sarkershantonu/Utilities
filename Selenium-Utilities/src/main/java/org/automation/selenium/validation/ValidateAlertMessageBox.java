package org.automation.selenium.validation;


import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 8/28/16.
 */
public abstract class ValidateAlertMessageBox extends SeleniumUtilBase {
    private Alert alert;
    public ValidateAlertMessageBox(Alert alert, WebDriver driver){
        super(driver);
        this.alert=alert;
    }
    public abstract void validateAllItems();
}

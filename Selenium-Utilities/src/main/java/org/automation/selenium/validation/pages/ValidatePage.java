package org.automation.selenium.validation.pages;

import org.automation.selenium.SeleniumUtilBase;
import org.automation.selenium.page.interfaces.CompletePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 8/28/16.
 */
public class ValidatePage {
    private CompletePage aCompletePage;

    public ValidatePage(CompletePage page) {
        aCompletePage =page;
    }

    // example validation
    public void validateTitle(String title){
        Assert.assertEquals(title,aCompletePage.getPageTitle());
    }

}

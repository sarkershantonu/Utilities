package org.automation.opencart.pages.common;

import lombok.Getter;
import org.automation.selenium.elements.ElementBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by shantonu on 5/18/16.
 */
public class SearchPanel extends ElementBase {
    @FindBy(xpath = "//div[@id='search']/input")
    private @Getter
    WebElement textBox ;

    @FindBy(xpath = "//div[@id='search']/span/button")//property loading or after parsing or static
    private @Getter WebElement button;

}

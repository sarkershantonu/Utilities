package org.automation.opencart.pages.common;

import lombok.Getter;
import org.automation.selenium.elements.ElementBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by shantonu on 5/16/16.
 */
public class TopBar extends ElementBase
{
    @FindBy(xpath = "")//property loading or after parsing or static
    @Getter private WebElement searchButton;

}

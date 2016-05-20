package project.selenium.pages.common;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.selenium.PageBase;

/**
 * Created by shantonu on 5/16/16.
 */
public class TopBar
{
    @FindBy(xpath = "")//property loading or after parsing or static
    @Getter private WebElement searchButton;

}

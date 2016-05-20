package project.selenium.pages.common;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

/**
 * Created by shantonu on 5/20/16.
 */
public class CartPreview {

    @FindBy(xpath = "")
    @Getter
    private WebElement[] addedItems;
    @FindBy(xpath = "")
    @Getter
    private WebElement cart;
    @FindBy(xpath = "")
    @Getter
    private WebElement viewCart;
    @FindBy(xpath = "")
    @Getter
    private WebElement checkOut;

}

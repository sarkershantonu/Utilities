package org.automation.opencart.pages.common;

import lombok.Getter;
import org.automation.selenium.elements.ElementBase;
import org.automation.selenium.page.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by shantonu on 5/20/16.
 */
public class CartPreview extends ElementBase {

    @FindBy(xpath = "")
    @Getter
    private WebElement cart;


    class CartSummary extends PageBase {
        private Entry[] addedItems;
        @FindBy(xpath = "")
        @Getter
        private WebElement viewCart;
        @FindBy(xpath = "")
        @Getter
        private WebElement checkOut;

        public CartSummary(WebDriver driver) {
            super(driver);
            for (int i = 0; i < getEntryNumber(); i++) {
                addedItems[i] = new Entry(driver);
            }
        }

        private int getEntryNumber() {
            return 2;//todo this will return number of items listed in preview.
        }
    }

    class Entry extends PageBase {
        @FindBy(xpath = "")
        @Getter
        private WebElement delete;

        @FindBy(xpath = "")
        @Getter
        private WebElement image;

        @FindBy(xpath = "")
        @Getter
        private WebElement title;

        public Entry(WebDriver driver) {
            super(driver);
        }
    }
}

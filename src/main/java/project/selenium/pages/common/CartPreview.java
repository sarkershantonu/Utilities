package project.selenium.pages.common;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import project.selenium.PageBase;

/**
 * Created by shantonu on 5/20/16.
 */
public class CartPreview extends PageBase {


    private Entry[] addedItems;
    @FindBy(xpath = "")
    @Getter
    private WebElement cart;
    @FindBy(xpath = "")
    @Getter
    private WebElement viewCart;
    @FindBy(xpath = "")
    @Getter
    private WebElement checkOut;

    public CartPreview(WebDriver driver){
        super(driver);
        for(int i=0; i<getEntryNumber(); i++){
            addedItems[i]=new Entry(driver);
        }
    }

    private int getEntryNumber(){
        return 2;//todo this will return number of items listed in preview.
    }

    class Entry extends PageBase{
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

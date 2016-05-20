package project.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.selenium.PageBase;

/**
 * Created by shantonu on 5/7/16.
 * //todo , clean up and match with real page item
 * Adding respective events
 *
 */
public class SearchResultPage extends PageBase{


    public void setPageLoadText(String pageLoadText) {

    }

    @FindBy(xpath = "")
    public WebElement searchResult_text;

    @FindBy(xpath = "")
    public WebElement searchCriteria_text;

    @FindBy(xpath = "")
    public WebElement serchKeywordTextBox;

    @FindBy(xpath = "")
    public WebElement calatagoryCombo;

    @FindBy(xpath = "")
    public WebElement searchInSub_checkBox;

    @FindBy(xpath = "")
    public WebElement searchInDescription_checkBox;

    @FindBy(xpath = "")
    public WebElement searchButton;

    @FindBy(xpath = "")
    public WebElement searchResult_title_text;

    @FindBy(xpath = "")
    public WebElement sortBy_text;

    @FindBy(xpath = "")
    public WebElement sortByCombo;

    @FindBy(xpath = "")
    public WebElement show_text;

    @FindBy(xpath = "")
    public WebElement showCombo;

    @FindBy(xpath = "")
    public WebElement productCompare_text;

    @FindBy(xpath = "")
    public WebElement productThumbrnelbutton_grid;

    @FindBy(xpath = "")
    public WebElement getProductThumbrnelbutton_list;

    @FindBy(xpath = "")
    public WebElement resultThumbnail_2;

    @FindBy(xpath = "")
    public WebElement resultThumbnail_3;
    @FindBy(xpath = "")
    public WebElement resultThumbnail_4;
    @FindBy(xpath = "")
    public WebElement resultThumbnail_1;


class Thumbnail{
    @FindBy(xpath = "")
    public WebElement imageOfProduct;

    @FindBy(xpath = "")
    public WebElement title_text;

    @FindBy(xpath = "")
    public WebElement description;

    @FindBy(xpath = "")
    public WebElement price;

    @FindBy(xpath = "")
    public WebElement addTocart_button;

    @FindBy(xpath = "")
    public WebElement wishlist_button;
    @FindBy(xpath = "")
    public WebElement compare_button;


}
}

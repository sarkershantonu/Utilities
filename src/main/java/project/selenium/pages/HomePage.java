package project.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.selenium.PageBase;
import project.selenium.actions.Click;


public class HomePage extends PageBase{

    @FindBy(xpath = "")
    public WebElement searchTextBox;

    @FindBy(xpath = "")//property loading or after parsing or static
    public WebElement searchButton;

    @Override
    public void setPageLoadText(String pageLoadText) {
        // todo
    }
    public Click<HomePage> click = new Click<HomePage>(this);
}

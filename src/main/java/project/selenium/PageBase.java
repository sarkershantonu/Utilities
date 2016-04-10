package project.selenium;

import org.browser.manage.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {
    protected String name;
    protected String url;
    protected WebDriver driver;
    protected String pageLoadedText ="";
    public abstract void setPageLoadText(String pageLoadText);

    public PageBase(){

    }
    public PageBase(WebDriver aDriver){
        this();
        this.driver =aDriver;
    }
    public <T extends PageBase> void initElement(T t){
        PageFactory.initElements(driver,t);
    }
    public PageBase verifyLoaded(){
       WebDriverWait wait = new WebDriverWait(driver, Browser.DEFAULT_WAIT_4_PAGE);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }
    /**
     * This will wati for another default time our time until curree url contains page url.
     * Avoid wait object referrencing for quick call, we will follow this
     */
    public PageBase verifyUrl(){
        (new WebDriverWait(driver, Browser.DEFAULT_WAIT_4_PAGE)).until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver) {
                return driver.getCurrentUrl() .contains(url);
            }
        });
        return this;
    }


}


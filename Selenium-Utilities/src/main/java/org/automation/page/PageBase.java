package org.automation.page;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

public abstract class PageBase {

    @Getter @Setter protected String name;
    @Getter @Setter protected String url;
    private WebDriver driver;


    /**
     * The text proves the page is loaded
     * @param pageLoadText
     */
    private String pageLoadedText ="";

    //todo , need fto decide how to validate load
    // public abstract void setPageLoadText(String pageLoadText);

    protected PageBase(){
        this.driver = Browser.getInstance();
        initElement(this);
    }
    protected PageBase(WebDriver aDriver){
        this.driver =aDriver;
        initElement(this);
    }

    /**
     * Init all web elements
     * @param t
     * @param <T>
     *
     */
    public <T extends PageBase> void initElement(T t){
        org.automation.PageFactory.initElements(driver,t);
    }

    /**
     * Todo => instead of checking only one Text, we need to implement all page item found loading. Which ensures page loading not just source parsing.
     */
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


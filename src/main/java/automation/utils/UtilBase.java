package automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shantonu on 4/10/16.
 * This is mother of all utils
 * Must wired with driver
 * Static method only contains global items, except that all are actually object items.
 */
public abstract class UtilBase {
    protected Logger log = null;
    protected WebDriver driver = null;
    protected JavascriptExecutor executor ;

    public UtilBase(WebDriver aDriver){
        this.driver = aDriver;
        log = LoggerFactory.getLogger(this.getClass());
    }
}

package automation.utils;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shantonu on 4/10/16.
 */
public abstract class UtilBase {
    protected Logger log = null;
    protected WebDriver driver = null;

    public UtilBase(WebDriver aDriver){
        this.driver = aDriver;
        log = LoggerFactory.getLogger(this.getClass());
    }
}

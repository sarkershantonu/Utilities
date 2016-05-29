package project.selenium.page.utils;

import automation.utils.UtilBase;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 5/29/16.
 */
public class WindowUtil extends UtilBase {
    private @Getter static String parentHandle = null;

    public WindowUtil(WebDriver aDriver) {
        super(aDriver);
        parentHandle = driver.getWindowHandle();
    }

}

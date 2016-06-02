package project.selenium.page.utils;

import automation.utils.UtilBase;
import lombok.Getter;
import org.browser.manage.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.internal.Streams;

import java.util.Set;

/**
 * Created by shantonu on 5/29/16.
 */
public class WindowUtil extends UtilBase {
    private @Getter static String parentHandle = null;

    public WindowUtil(WebDriver aDriver) {
        super(aDriver);
        parentHandle = driver.getWindowHandle();
        executor =(JavascriptExecutor)driver;
    }

    public String createWindows(){
        Set<String> handles = Browser.getInstance().getWindowHandles();

    }
    public String createWindow(){
        String url = "";
        String name = "blank";
        return createWindow(url, name);
    }
    public String createBlankWindow(){
        executor.executeScript("window.open()");
        return driver.getWindowHandle();
    }
    public String createWindow(String url){
        return createWindow(url, "A_new_window_url");
    }
    public String createWindow(String url, String name){
        String options =  "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes";
        return createWindow(url,name,options);
    }
    public String createWindow(String url, String name, String option){

        executor.executeScript("window.open(\"arguments[0]\",\"arguments[1]\", \"arguments[2]\")", new Object[]{url, name,option});
        return driver.getWindowHandle();
    }
}

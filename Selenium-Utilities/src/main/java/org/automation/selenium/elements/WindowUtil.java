package org.automation.selenium.elements;

import automation.utils.UtilBase;
import lombok.Getter;
import org.browser.manage.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by shantonu on 5/29/16.
 */
public class WindowUtil extends UtilBase {
    private @Getter static String parentHandle = null;
    private @Getter static String newHandle = null;

    public WindowUtil(WebDriver aDriver) {
        super(aDriver);
        parentHandle = driver.getWindowHandle();
        executor =(JavascriptExecutor)driver;

    }


    /**
     * TODO
     * @param old
     * @return
     */
    public String getNewHandle(Set<String> old){
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(old);
        if(handles.iterator().hasNext()){
            return handles.iterator().next();
        }
        return null;
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
    
    public void switchTo(String window,int sec){
        WebDriverWait wait =  Browser.setWebDriverWait(sec);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver aDriver) {
                aDriver = driver;
                Set<String> handles = aDriver.getWindowHandles();
                for(String handle : handles){
                    aDriver.switchTo().window(handle);
                    if(aDriver.getTitle().contains(window)||aDriver.getCurrentUrl().contains(window)){
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * todo
     * @param name
     * @return
     */
    public boolean isChildWindow(String name){
        return false;
    }
    //todo
    public boolean isChildWindow(){
        return false;
    }
    //todo
    public void closeWindow(String windowHandle){

    }
    //todo
    public void closeChildWindow(String parentHandle){

    }
    //todo
    public void closeChildWindow(){

    }
}

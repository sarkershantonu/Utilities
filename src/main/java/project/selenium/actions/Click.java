package project.selenium.actions;

import org.browser.manage.Browser;
import org.openqa.selenium.WebElement;
import project.selenium.PageBase;
import project.selenium.page.utils.JavaScriptUtil;

/**
 * Created by shantonu on 4/21/16.
 */
public class Click<T extends PageBase> extends MouseBase<T> {

    private static String clickJS = "arguments[0].click();";
    public Click(T t) {
        super(t);
    }
    @Override
    T on(WebElement element, int second) {
        element.click();
        return page;
    }
    T byJS(WebElement element, int second) {
        try {
            new JavaScriptUtil(Browser.getInstance()).getJsExecutor().executeScript(clickJS, new Object[]{element});
        }catch (Exception e)
        {
            //todo make own exception type and add logging
        }
        return page;
    }

}

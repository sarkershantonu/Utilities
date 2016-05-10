package project.selenium.actions;

import org.browser.manage.Browser;
import org.openqa.selenium.WebElement;
import project.selenium.PageBase;
import project.selenium.page.utils.JavaScriptUtil;

/**
 * Created by shantonu on 4/21/16.
 */
public class Click<T extends PageBase> extends MouseBase<T> {

    public Click(T t) {
        super(t);
    }
    @Override
    T perform(WebElement element, int second) {
        element.click();
        return page;
    }
    T performByJS(WebElement element, int second) {
        new JavaScriptUtil(Browser.getInstance()).getJsExecutor().
        return page;
    }

}

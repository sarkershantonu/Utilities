package org.automation.actions.mouse;

import org.browser.manage.Browser;
import org.openqa.selenium.WebElement;
import project.selenium.PageBase;


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
       Browser.getJSexcutor().executeScript(clickJS, new Object[]{element});
        return page;
    }

}

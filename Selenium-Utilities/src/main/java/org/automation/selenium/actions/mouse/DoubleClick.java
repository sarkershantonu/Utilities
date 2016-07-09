package org.automation.selenium.actions.mouse;


import org.automation.selenium.browser.Browser;
import org.automation.selenium.page.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


/**
 * Created by shantonu on 4/21/16.
 */
public class DoubleClick<T extends PageBase> extends MouseBase<T> {

    public DoubleClick(T t) {
        super(t);
    }

    @Override
    T on(WebElement element, int second) {
        action = new Actions(Browser.getInstance());
        action.doubleClick(element);
        return page;
    }
}

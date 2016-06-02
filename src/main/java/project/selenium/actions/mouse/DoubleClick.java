package project.selenium.actions.mouse;

import org.browser.manage.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import project.selenium.PageBase;
import project.selenium.actions.MouseBase;

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

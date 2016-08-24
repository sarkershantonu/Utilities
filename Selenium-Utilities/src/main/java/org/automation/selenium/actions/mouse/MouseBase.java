package org.automation.selenium.actions.mouse;

import org.automation.selenium.page.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


/**
 * Created by shantonu on 4/21/16.
 * All actions will perform on a web element and return the page it self.
 */
public abstract class MouseBase<T extends PageBase> {
    protected Actions action;
    protected T page;

    protected MouseBase(T t){
        this.page=t;
    }
    public T on(WebElement element){
        return on(element, 0);
    }

    /***
     * The must implementation for a particular action events.
     * @param element
     * @param second => wait on web element
     * @return
     */
    public abstract T  on(WebElement element, int second) ;
}

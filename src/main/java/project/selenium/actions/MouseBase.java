package project.selenium.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import project.selenium.PageBase;

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
    abstract T  on(WebElement element, int second) ;
}

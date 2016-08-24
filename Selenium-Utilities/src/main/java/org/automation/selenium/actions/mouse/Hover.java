package org.automation.selenium.actions.mouse;

import org.automation.selenium.browser.Browser;
import org.automation.selenium.page.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Hover<T extends PageBase> extends MouseBase<T>
{
        public void hoverOn(WebElement element) {// example only, no use
        action = new Actions(Browser.getInstance());
        action.moveToElement(element).perform();
    }
     public Hover(T t) {
        super(t);
    }


    public T on(WebElement element, int second) {//apply for each page...
        action = new Actions(Browser.getInstance());
        action.moveToElement(element).perform();
        return page;
    }
    
      T byJS(WebElement element, int second) {
       //todo mouse hover by javaScript
        return page;
    }

}

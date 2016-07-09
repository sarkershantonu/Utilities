package org.automation.selenium.actions;

import org.browser.manage.Browser;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import project.selenium.PageBase;

import java.util.Arrays;

/**
 * Created by shantonu on 5/11/16.
 */
public class KeyPress<T extends PageBase> {

    protected T page;
    public KeyPress(T t) {
        this.page = t;
    }

    T perform(WebElement element,String text, int second) {
        element.sendKeys(text);
        return page;
    }

    T perform(WebElement element,Keys key, int second) {
        element.sendKeys(key);

        return page;
    }

    T perform(WebElement element,char asciiValue, int second) {
        element.sendKeys(Character.toString(asciiValue));
        return page;
    }
    /**
     * Suppport 1 (F1), 2(ctrl+4) or 3(CTRL+ALT+k) input keys
     * @param element
     * @param keys
     * @param second
     * @return
     * Todo fine tuning , testing, making a whole new util for specifying keys based on type of keys.
     */
    T perform(WebElement element,Keys[] keys, int second) {
        Actions keypress = new Actions(Browser.getInstance());
        if(keys.length==3){
            keypress.keyDown(element,keys[0]);
            keypress.sendKeys(element,keys[2]);
            keypress.keyUp(element,keys[1]);
        }
        else if(keys.length==2){
            keypress.keyDown(element,keys[0]);
            keypress.keyUp(element,keys[1]);

        }else if(keys.length==1){
            perform(element,keys[0],second);
        }else{
            // no action perform, invalid operation, todo exception + logging
        }

        return page;
    }

    T perform(Keys[] keys, int second, WebElement element) {
        element.sendKeys(Keys.chord(Arrays.asList(keys)));
        return page;
    }
    public void type(WebElement element, CharSequence... charSequence){
        StringBuilder sb = new StringBuilder();
        for(CharSequence c : charSequence){
            sb.append(c);
        }
        element.clear();
        //element.sendKeys(charSequence.toString()); // refactored with following
        element.sendKeys(sb);
    }
}

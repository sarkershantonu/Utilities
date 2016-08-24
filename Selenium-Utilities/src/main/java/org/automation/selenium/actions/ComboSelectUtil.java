package org.automation.selenium.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by shantonu on 8/24/16.
 */
public class ComboSelectUtil {

    public static void selectBy(WebElement element, String name){
        new Select(element).selectByVisibleText(name);
    }
    public static void selectByValue(WebElement element, String value){
        new Select(element).selectByValue(value);
    }
    public static void selectBy(WebElement element, int index){
        new Select(element).selectByIndex(index);
    }
    public static void disSelectAl(WebElement element){
        new Select(element).deselectAll();
    }
    public static void disSelectBy(WebElement element, int index){
        new Select(element).deselectByIndex(index);
    }
    public static void disSelectBy(WebElement element, String name){
        new Select(element).deselectByVisibleText(name);
    }
    public static void disSelectByValue(WebElement element, String value){
        new Select(element).deselectByValue(value);
    }
    public static List<WebElement> getAllOptions(WebElement element){
        return new Select(element).getOptions();
    }
    public static List<WebElement> getAllSelectedOptions(WebElement element){
        return new Select(element).getAllSelectedOptions();
    }
}

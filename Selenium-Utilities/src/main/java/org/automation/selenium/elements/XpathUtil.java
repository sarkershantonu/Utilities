package org.automation.selenium.elements;

/**
 * Created by shantonu on 6/6/16.
 * This class is helper for xpath finding.This class will be big and page specific.
 * todo => need to check opencart pages to define the prefix & post fix
 */
public class XpathUtil {
    /**
     * This is an example method, idea is simple, when ever we need to find xpath, we can use level names to get that.
     * Mostly usable for retrying .
     * @param name
     * @return
     */
    public static String getButtonXpath(String name){
        return "//*[@id='domainGroupChoice']//a[text()='" + name + "' and not(contains(@style, 'none'))]";
    }
}

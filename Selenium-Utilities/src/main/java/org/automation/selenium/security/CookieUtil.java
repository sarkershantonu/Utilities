package org.automation.selenium.security;

import lombok.Getter;
import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * Created by shantonu on 6/2/16.
 */
public class CookieUtil extends SeleniumUtilBase {
    private @Getter
    Set<Cookie> browserCookies = null;
    public CookieUtil(WebDriver aDriver) {
        super(aDriver);
        init();
    }

    private  void init(){
        browserCookies = driver.manage().getCookies();
    }
    public void clearCookie(){
        driver.manage().deleteAllCookies();
        init();
    }
    public void add(Cookie cookie){
        driver.manage().addCookie(cookie);
    }

    public void delete(Cookie cookie){
        driver.manage().deleteCookie(cookie);
    }
    public void delete(String cookieName){
        driver.manage().deleteCookieNamed(cookieName);
    }
    public Cookie getCookie(String name){
        return driver.manage().getCookieNamed(name);
    }

    public WebDriver.Options getCookieOptions(){
        return driver.manage();
    }
}

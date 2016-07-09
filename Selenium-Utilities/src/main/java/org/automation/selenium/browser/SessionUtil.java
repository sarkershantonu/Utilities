package org.automation.selenium.browser;

import org.automation.selenium.users.UserInfo;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shantonu on 6/12/16.
 * this is responsible for managing multiple session. The whole execution will be depend on those behaviots
 */
public class SessionUtil {
    private static final Map<UserInfo, WebDriver> sessions = new HashMap();
    public static WebDriver getASession(UserInfo user){
        return sessions.get(user);
    }
    public static void storeASession(UserInfo user, WebDriver driver){
        sessions.put(user, driver);
    }
    public static void removeASession(UserInfo user){
        sessions.remove(user);
    }
    public static void removeASession(UserInfo user, WebDriver driver){
        sessions.remove(user, driver);
    }

}

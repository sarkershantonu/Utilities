package org.automation.angular.scripts;

/**
 * Created by shantonu on 8/9/16.
 */
public class jsRunException extends Exception {
    public jsRunException(String message){
        super(message);
    }
    public jsRunException(String message, String js){
        super(js+ " was failed to run => "+message);
    }
}

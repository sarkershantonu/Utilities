package org.automation.utils.common;

/**
 * Created by shantonu on 9/4/16.
 */
public class RuntimeUtil {

    public static Long getFreeMemory(){
        return Runtime.getRuntime().freeMemory();
    }
}

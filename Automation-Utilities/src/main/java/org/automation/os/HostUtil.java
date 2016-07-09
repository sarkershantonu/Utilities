package org.automation.os;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by shantonu on 6/3/16.
 */
public class HostUtil {
    public static String getHostName(){
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            return e.getMessage();
        }
    }
}

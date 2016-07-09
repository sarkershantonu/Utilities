package automation.utils;

import java.util.Base64;

/**
 * Created by shantonu on 7/9/16.
 */
public class CryptoUtil {

    public static String encrypt(String item){
        byte[] encrypted =  Base64.getEncoder().encode(item.getBytes());
        return new String(encrypted);
    }

}

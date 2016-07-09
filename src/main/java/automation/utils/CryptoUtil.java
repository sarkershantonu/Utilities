package automation.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by shantonu on 7/9/16.
 */
public class CryptoUtil {

    public static String encrypt(String item){
        byte[] encrypted =  Base64.getEncoder().encode(item.getBytes(StandardCharsets.ISO_8859_1));//default Charset
        return new String(encrypted);
    }
    public static String encrypt(String item, Charset charset){
        byte[] encrypted =  Base64.getEncoder().encode(item.getBytes(charset));
        return new String(encrypted);
    }
    public static String dencrypt(String item){
        byte[] encrypted =  Base64.getDecoder().decode(item);
        return new String(encrypted);
    }
    public static String dencrypt(String item, Charset charset){
        byte[] encrypted =  Base64.getDecoder().decode(item.getBytes(charset));
        return new String(encrypted);
    }
}

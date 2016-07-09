package org.automation.common;

import java.security.SecureRandom;
import java.util.List;

/**
 * Created by shantonu on 6/1/16.
 */
public class RandomeUtil {
    static final String allNumericCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    public static Integer getInt(int a, int b){

        return new SecureRandom().nextInt(b-a+1)+a;
    }
    public static String getString(int len){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( allNumericCharacters.charAt( rnd.nextInt(allNumericCharacters.length()) ) );
        return sb.toString();

    }

    public static <T> T getRandomItem(List<T> items){
        int max = items.size()-1;
        int random = getInt(0,max).intValue();
        return items.get(random);
    }
}

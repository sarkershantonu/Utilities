package automation.utils;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by shantonu on 6/1/16.
 */
public class RandomeUtil {
    public static Integer getInt(int a, int b){

        return new Random().nextInt(b-a+1)+a;
    }
    public static String getString(String a, String b){

    }

    public static <T> T getItem(List<T> items){
        int max = items.size()-1;
        int random = getInt(0,max).intValue();
        return items.get(random);
    }
}

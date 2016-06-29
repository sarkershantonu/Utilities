package automation.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shantonu on 6/29/16.
 */
public class CLIUtil {

    private static Map<String, String> arguments = new HashMap<>();
    private static String regix = "(^-)(?<name>[^=]+)(=)(?<value>.*$)";

    private CLIUtil(){}
    /**
     * Explanation of regular expression
     * The arguments will be start with -
     *
     *start with =
     *
     * name = name of argument, value = value of the argument
     * This is inside regix pattern to specify grouping with names, to identify common patterns
     * Group 1 = just -
     * group 2 = argument > I am specifying the groupname as name
     * group 3 = value
     * group 4 = value > I am specifying the groupname as value
     */

    public static Map<String, String> argumentParser(String... inputs){
        final String[] args = Arrays.copyOf(inputs, inputs.length);
        final Pattern pat = Pattern.compile(regix);

        for(final String arg : args){
            final Matcher match = pat.matcher(arg);
            if(match.find()){
                final String name = match.group("name");
                final String value = match.group("value");
                arguments.put(name,value);// you may check duplicate, I am replacing cli parameter with
            }
        }
        return Collections.unmodifiableMap(arguments);
    }

    public static void main(String... args){
        Map<String, String> items = new HashMap<>();
        items = argumentParser(args);

        for (String key : items.keySet()){
            System.out.println("Name = "+key +" : Valeue = "+items.get(key));
        }
    }
}

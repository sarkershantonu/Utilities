package automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is created to unitfied logging, it is now default behavior
 * Todo => addind extra logging runtime behavior while creating log instance. (like environment depnendent)
 */

public class LogManager {
    private static Logger log = null;

    public static Logger getLogger(Class aClass){

        log = LoggerFactory.getLogger(aClass);
        return log;
    }

}

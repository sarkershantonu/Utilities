package org.automation.utils.tracking.logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.unitils.core.UnitilsException;
import org.unitils.util.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class Log4jAppenderTestNG extends AppenderSkeleton {
    @Override
    protected void append(LoggingEvent event) {
        Class reporterClass;
        try {
            reporterClass = ReflectionUtils.getClassWithName("org.testng.Reporter");
        } catch (UnitilsException e) {
            return;
        }
        Method logMethod = null;
        try {
            logMethod = reporterClass.getMethod("log", String.class, int.class);
            logMethod.invoke(null, escapeHtml(event.getRenderedMessage()), 0);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}

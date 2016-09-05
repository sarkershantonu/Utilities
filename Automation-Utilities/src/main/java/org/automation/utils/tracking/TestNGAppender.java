package org.automation.utils.tracking;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.unitils.core.UnitilsException;
import org.unitils.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;


public class TestNGAppender extends AppenderSkeleton {
    /**
     * Subclasses of <code>AppenderSkeleton</code> should implement this
     * method to perform actual logging. See also {@link #doAppend
     * AppenderSkeleton.doAppend} method.
     *
     * @since 0.9.0
     */
    @Override
    protected void append(LoggingEvent event) {
        Class reporterClass;
        try {
            reporterClass = ReflectionUtils.getClassWithName("org.testng.Reporter");
        } catch (UnitilsException e) {
            //TestNG appender just does nothing if testng is not in class path.
            return;
        }
        //ReflectionUtils.invokeMethod
        Method logMethod = null;
        try {
            logMethod = reporterClass.getMethod("log", String.class, int.class);
            logMethod.invoke(null, escapeHtml(event.getRenderedMessage()), 0);
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        //Reporter.log(event.getRenderedMessage(), 0);
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void close() {
        //Nothing to do
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}

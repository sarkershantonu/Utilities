package org.automation.angular;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.internal.WrapsDriver;

/**
 * Allows browser to manager specif items supporting angular
 * these are major items to overrid (element find+ jsrunner+ get driver
 */
public interface NgSupport extends SearchContext,JavascriptExecutor ,WrapsDriver {
    void waitForAngular();
    JavascriptExecutor getJsExecutor();
}

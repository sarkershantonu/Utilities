package org.automation.angular.extras.find;

import org.automation.angular.WebDriverNG;
import org.automation.angular.WebElementNG;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.find.BaseFinder;
import org.openqa.selenium.lift.find.Finder;

import java.util.Collection;

/**
 * Created by shantonu on 9/2/16.
 * todo
 */
public class HtmlTagFinderNG extends BaseFinder<WebElementNG, WebDriverNG> {


    @Override
    protected Collection<WebElementNG> extractFrom(WebDriverNG context) {
        return null;
    }

    @Override
    protected void describeTargetTo(Description description) {

    }
}

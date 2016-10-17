package org.automation.angular.extras.find;

import org.hamcrest.Factory;
import org.openqa.selenium.lift.find.HtmlTagFinder;

import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.match.AttributeMatcher.attribute;

/**
 * Created by shantonu on 9/2/16.
 * todo
 */
public class DivFinderNG extends HtmlTagFinder {
    @Override
    protected String tagName() {
        return null;
    }

    @Override
    protected String tagDescription() {
        return null;
    }

    @Factory
    public static HtmlTagFinder divng() {
        return new DivFinderNG();
    }

    public static HtmlTagFinder div(String id) {
        return divng().with(attribute("id", equalTo(id)));// todo
    }
}

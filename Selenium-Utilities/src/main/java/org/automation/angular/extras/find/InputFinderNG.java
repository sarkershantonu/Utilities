package org.automation.angular.extras.find;

import org.hamcrest.Factory;
import org.openqa.selenium.lift.find.HtmlTagFinder;

import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.openqa.selenium.lift.Matchers.value;

/**
 * Created by shantonu on 9/2/16.
 */
public class InputFinderNG extends HtmlTagFinderNG {
    @Override
    protected String tagDescription() {
        return "input field";
    }

    @Override
    protected String tagName() {
        return "input";
    }

    @Factory
    public static HtmlTagFinder textbox() {
        return new InputFinderNG().with(attribute("type", equalTo("text")));
    }

    @Factory
    public static HtmlTagFinder imageButton() {
        return new InputFinderNG().with(attribute("type", equalTo("image")));
    }

    @Factory
    public static HtmlTagFinder imageButton(String label) {
        return imageButton().with(value((label)));
    }

    @Factory
    public static HtmlTagFinder radioButton() {
        return new InputFinderNG().with(attribute("type", equalTo("radio")));
    }

    @Factory
    public static HtmlTagFinder radioButton(String id) {
        return radioButton().with(attribute("id", equalTo(id)));
    }

    @Factory
    public static HtmlTagFinder submitButton() {
        return new InputFinderNG().with(attribute("type", equalTo("submit")));
    }

    @Factory
    public static HtmlTagFinder submitButton(String label) {
        return submitButton().with(value(label));
    }
}

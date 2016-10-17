package org.automation.angular.extras.find;

import org.automation.angular.WebDriverNG;
import org.automation.angular.WebElementNG;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.openqa.selenium.lift.find.BaseFinder;

/**
 * Created by shantonu on 9/2/16.
 * todo : whole function will need to adopet angular
 */
public abstract class HtmlTagFinderNG extends BaseFinder<WebElementNG, WebDriverNG> {



  /*  @Override
    protected Collection<WebElement> extractFrom(WebDriverNG context) {
        return context.findElements(By.xpath("//" + tagName()));
    }
*/
    @Override
    protected void describeTargetTo(Description description) {
        description.appendText(tagDescription());
    }

    @Override // more specific return type
    public HtmlTagFinderNG with(Matcher<WebElementNG> matcher) {
        super.with(matcher);
        return this;
    }


    protected abstract String tagName();

    protected abstract String tagDescription();
}

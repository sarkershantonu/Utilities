package org.automation.angular.scripts;

import org.automation.angular.scripts.io.Loader;

/**
 * @author Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */
public final class FindRepeaterElements implements Script {
    @Override
    public String content() {
        return new Loader("repeaterElement").content();
    }
}

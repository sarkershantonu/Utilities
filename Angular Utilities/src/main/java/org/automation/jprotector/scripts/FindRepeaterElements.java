package org.automation.jprotector.scripts;

import org.automation.jprotector.scripts.io.Loader;

/**
 * @author Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */
public final class FindRepeaterElements implements Script {
    @Override
    public String content() {
        return new Loader("repeaterElement").content();
    }
}

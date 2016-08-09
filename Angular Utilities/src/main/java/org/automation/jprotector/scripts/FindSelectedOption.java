package org.automation.jprotector.scripts;

import org.automation.jprotector.scripts.io.Loader;

/**
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 */
public final class FindSelectedOption implements Script {
    @Override
    public String content() {
        return new Loader("selectedOption").content();
    }
}

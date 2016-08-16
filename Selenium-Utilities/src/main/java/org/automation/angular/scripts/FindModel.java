package org.automation.angular.scripts;

import org.automation.angular.scripts.io.Loader;

/**
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 */
public final class FindModel implements Script {
    @Override
    public String content() {
        return new Loader("model").content();
    }
}
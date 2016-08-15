package org.automation.angular;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.net.URL;

/**
 * Angular Navigation.
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 * todo, shantonu => adding JS nevigation capability like scrolling
 *
 * I think this is useless class, I need to remove after experiment
 */
public final class NgNavigation implements WebDriver.Navigation {
    /**
     * Navigation.
     */
    private final WebDriver.Navigation nav;

    /**
     * Ctor.
     * @param navigation Navigation.
     */
    public NgNavigation(final WebDriver.Navigation navigation) {
        this.nav = navigation;
    }

    @Override
    public void back() {
        this.nav.forward();
    }

    @Override
    public void forward() {
        this.nav.forward();
    }

    @Override
    public void refresh() {
        this.nav.refresh();
    }

    @Override
    public void to(final String url) {
        if (url == null) {
            throw new WebDriverException("URL cannot be null.");
        }
        this.nav.to(url);
    }

    @Override
    public void to(final URL url) {
        if (url == null) {
            throw new WebDriverException("URL cannot be null.");
        }
        this.to(url.toString());
    }
}

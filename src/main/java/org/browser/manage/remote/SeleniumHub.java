package org.browser.manage.remote;

import org.browser.manage.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

/**
 * Created by shantonu on 7/1/16.
 * <p>
 *  just another parallel web driver manage util as hub. concurrent semaphore added.
 */
public class SeleniumHub {
    private Semaphore available = new Semaphore(1, true);
    private static int counter = 0;
    private int timeout;

    private Queue<WebDriver> drivers = new LinkedList<>();
    private final LinkedList<RemoteWDConnector> remoteWDConnectors = new LinkedList<>();
    private BlockingDeque<RemoteWebDriver> remoteWebDrivers = new LinkedBlockingDeque<>();

    private final boolean isReeusable;
    private final boolean isRemote;

    public SeleniumHub(boolean isReeusable, boolean isRemote, int timeout, int ready, String... servers) {
        this.isReeusable = isReeusable;
        this.isRemote = isRemote;
        this.timeout = timeout;
        this.ready(ready);
        for (final String server : servers) {
            RemoteWDConnector connector = new RemoteWDConnector(server, this.remoteWebDrivers);
            connector.start();
            this.remoteWDConnectors.add(connector);
        }
    }

    private WebDriver init() {
        WebDriver dr = null;
        if (isRemote) {
            while (dr == null && !Thread.currentThread().isInterrupted()) {
                try {
                    dr = remoteWebDrivers.take();
                } catch (WebDriverException e) {
                    if (e.getMessage().contains("session cannot find")) {
                        try {
                            Thread.currentThread().interrupt();
                            dr = BrowserFactory.getDefaultRemoteDriver();
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        } else {
            dr = BrowserFactory.getDefaultDriver();
        }
        return dr;
    }

    public void ready(int drivers) {
        synchronized (this) {
            this.available.release(drivers);
        }
    }

    public WebDriver getDriver() {
        try {
            this.available.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriver driver = null;
        if (isReeusable) {
            driver = this.drivers.poll();
        }
        if (driver.equals(null)) {
            driver = init();
        }
        return driver;
    }

    /***
     * Release web driver
     * Quit it
     *
     * @param driver
     */
    public void free(WebDriver driver) {
        synchronized (this) {
            if (isReeusable) {
                this.drivers.add(driver);
            } else {
                driver.quit();
            }
            this.available.release();
        }
    }

    public WebDriverWait wait(WebDriver driver) {
        return new WebDriverWait(driver, timeout);
    }

    public void quit() {
        quitAllConnecctors();
        quitAllRemoteDrivers();
        quitAllDrivers();
    }

    private void quitAllConnecctors() {
        for (final RemoteWDConnector connector : this.remoteWDConnectors) {
            connector.interrupt();
        }
    }

    private void quitAllRemoteDrivers() {
        WebDriver driver = null;
        while ((driver = remoteWebDrivers.poll()) != null) {
            driver.quit();
        }
    }

    private void quitAllDrivers() {
        WebDriver driver = null;
        while ((driver = drivers.poll()) != null) {
            driver.quit();
        }
    }
}

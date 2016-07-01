package org.browser.manage.remote;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Semaphore;

/**
 * Created by shantonu on 7/1/16.
 *
 * todo , implement a managable parallal system driven by remote drivers (local support also)
 */
public class SeleniumHub {
    private Semaphore available = new Semaphore(1, true);

    private Queue<WebDriver> drivers = new LinkedList<>();

    private BlockingDeque<RemoteWebDriver> remoteWebDrivers;

    private final boolean isReeusable;
    private final boolean isRemote ;

    public SeleniumHub(Queue<WebDriver> drivers,boolean isReeusable ,boolean isRemote) {
        this.drivers = drivers;
        this.isReeusable = isReeusable;
        this.isRemote = isRemote;


    }
}

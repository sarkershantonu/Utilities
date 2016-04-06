package org.manage.browser;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by shantonu on 4/6/16.
 */
public class DesiredcapabilityFactory {
    private static DesiredCapabilities capabilities;
    private DesiredcapabilityFactory(){}
    public static DesiredCapabilities getCapability(String nameOfBrwser){

        return capabilities;
    }
}

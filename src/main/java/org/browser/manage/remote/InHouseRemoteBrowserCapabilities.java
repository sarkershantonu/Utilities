package org.browser.manage.remote;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by shantonu on 5/3/16.
 */
public class InHouseRemoteBrowserCapabilities {

    private static DesiredCapabilities cap = null;

    /**
     * This will give local Grid wise capabilities.
     * most of item should come from property
     * But, condition should be there to choose which to take
     */
    public static DesiredCapabilities getCapability(String config){

        if(config==""){

        }
        else if(config==""){

        }
        else
        {
            cap =  getDefaultCapability();
        }
        return cap;
    }

    private static DesiredCapabilities getDefaultCapability(){
        cap = DesiredCapabilities.firefox();
        //todo , read from property
        return cap;
    }

}

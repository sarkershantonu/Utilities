package org.automation.utils;

import automation.utils.UtilBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by shantonu on 5/27/16.
 */
public class PerformanceTimingUtil extends UtilBase{
    private Map<String, Object> timing = null;
    private static final String JS4perf="var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {};var timings = performance.timing || {};return timings;";

    public PerformanceTimingUtil(WebDriver aDriver) {
        super(aDriver);
    }

    public  Map<String, Object> getAllNavigationTiming(){
        timing = new HashMap<String, Object>();
        JavascriptExecutor jsRunner = (JavascriptExecutor) driver;
        timing = (Map<String, Object>)jsRunner.executeAsyncScript(JS4perf);
        return timing;
    }

    public Long getloadignTime(){
        return getTime("loadEventEnd");
    }
    private Long getTime(String name){
        // this is in nano second (Long) timing.get(name));
        return TimeUnit.MILLISECONDS.toSeconds((Long) timing.get(name));
    }

    public Long getnavigationStart(){        return getTime("navigationStart");    }
    public Long getunloadEventStart(){        return getTime("unloadEventStart");    }
    public Long getunloadEventEnd(){        return getTime("unloadEventEnd");    }
    public Long getredirectStart(){        return getTime("redirectStart");    }
    public Long getredirectEnd(){        return getTime("redirectEnd");    }
    public Long getfetchStart(){        return getTime("fetchStart");    }
    public Long getdomainLookupStart(){        return getTime("domainLookupStart");    }
    public Long getdomainLookupEnd(){        return getTime("domainLookupEnd");    }
    public Long getconnectStart(){        return getTime("connectStart");    }
    public Long getconnectEnd(){        return getTime("connectEnd");    }
    public Long getsecureConnectionStart(){        return getTime("secureConnectionStart");    }
    public Long getrequestStart(){        return getTime("requestStart");    }
    public Long getresponseStart(){        return getTime("responseStart");    }
    public Long getresponseEnd(){        return getTime("responseEnd");    }
    public Long getdomLoading(){        return getTime("domLoading");    }
    public Long getdomInteractive(){        return getTime("domInteractive");    }
    public Long getdomContentLoadedEventStart(){        return getTime("domContentLoadedEventStart");    }
    public Long getdomContentLoadedEventEnd(){        return getTime("domContentLoadedEventEnd");    }
    public Long getdomComplete(){        return getTime("domComplete");    }
    public Long getloadEventStart(){        return getTime("loadEventStart");    }
    public Long getloadEventEnd(){        return getTime("loadEventEnd");    }

    /* todo => based on timing, we can give different performance timing functions
     * example : Total Load time = load event end - get request start
     * or loading Content time =getdomContentLoadedEventEnd - getdomContentLoadedEventStart
    */
}

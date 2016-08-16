package org.automation.selenium.page;

import org.apache.commons.io.IOUtils;
import org.automation.selenium.SeleniumUtilBase;
import org.automation.utils.tracking.error.ExceptionManager;
import org.automation.utils.tracking.error.TestError;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by shantonu on 4/19/16.
 */
public class PageSourceUtil extends SeleniumUtilBase {

    public PageSourceUtil(WebDriver aDriver) {
        super(aDriver);
    }
    public String readJsLibrary(String jsFileName){
        InputStream input  = this.getResourceAsStream(jsFileName);
        String jsLib = "" ;
        try {
            jsLib = IOUtils.toString(input);
        } catch (IOException e) {
            log.info("Error Loading : "+ jsFileName + "\n"+e.getMessage());
            ExceptionManager.performDefaultAction("Error Loading : "+ jsFileName , e, new TestError());

        }finally {
            IOUtils.closeQuietly(input);
        }
        return jsLib;
    }
}

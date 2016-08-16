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
public class SourceUtil extends SeleniumUtilBase {


    public SourceUtil(WebDriver aDriver) {
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

    //experiments => JS injection
   public class AddJs extends SeleniumUtilBase{
        private String screenshotLib = "<script type=\"text/javascript\" src=\"html2canvas.min.js\"></script>";

        private String ScriptAdding ="function load_script( source ) {\n" +
                "    var new_script  = document.createElement('script');\n" +
                "    new_script.type = 'text/javascript';\n" +
                "    new_script.src = source;\n" +
                "    new_script.className = 'MyInjectedScript';\n" +
                "    document.getElementsByTagName('head')[0].appendChild(new_script);\n" +
                "}";

        public AddJs(WebDriver aDriver) {
            super(aDriver);
        }
    }
}


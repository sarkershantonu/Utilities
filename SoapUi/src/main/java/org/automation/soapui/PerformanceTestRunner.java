package org.automation.soapui;

import com.eviware.soapui.tools.SoapUILoadTestRunner;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

/**
 * Created by shantonu on 7/12/17.
 */
public class PerformanceTestRunner extends SoapUiTCRunner implements Runnable{

    private SoapUILoadTestRunner runner;

    private PerformanceTestRunner(){
        runner = new SoapUILoadTestRunner();
    }
    @Override
    public void run() {
        try {
            runner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

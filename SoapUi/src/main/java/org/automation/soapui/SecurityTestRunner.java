package org.automation.soapui;

import com.eviware.soapui.tools.SoapUISecurityTestRunner;


/**
 * Created by shantonu on 7/12/17.
 */
public class SecurityTestRunner extends SoapUiTCRunner implements Runnable{
    private SoapUISecurityTestRunner runner;


    @Override
    public void run() {
        try {
            runner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.automation.soapui;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

/**
 * Created by shantonu on 7/8/17.
 */
public class SoapUiRunner implements Runnable{
    private SoapUITestCaseRunner runner;
    private String path_xml_tc;

    private SoapUiRunner(){
        runner = new SoapUITestCaseRunner();
    }

    private SoapUiRunner(String soap_ui_tc_path) {
        this();
        this.path_xml_tc = soap_ui_tc_path;
    }

    public void runWithTestCase(String path) {

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

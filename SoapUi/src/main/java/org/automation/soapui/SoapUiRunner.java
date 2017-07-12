package org.automation.soapui;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

/**
 * Created by shantonu on 7/8/17.
 */
public class SoapUiRunner implements Runnable{
    private SoapUITestCaseRunner runner;
    private String path_xml_tc;
    private String[] prop;
    private SoapUiRunner(){
        runner = new SoapUITestCaseRunner();

    }

    public SoapUiRunner(String soap_ui_tc_path) {
        this();
        this.path_xml_tc = soap_ui_tc_path;
    }
    public SoapUiRunner(String soap_ui_tc_path, String[] properties) {
        this(soap_ui_tc_path);
        this.prop=properties;
    }
    private void init(){
        runner.setIgnoreErrors(false);
        runner.setExportAll(true);
        runner.setJUnitReport(true);
        runner.setPrintReport(true);
        runner.setEnableUI(true);
    }

    public void runWithTestCase(String path) {

    }

    @Override
    public void run() {
        try {
            init();
            runner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

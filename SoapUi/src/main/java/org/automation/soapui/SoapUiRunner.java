package org.automation.soapui;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by shantonu on 7/8/17.
 */
public class SoapUiRunner implements Runnable{
    private String suit_name;
    private String tc_name;
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
    public SoapUiRunner(String soap_ui_tc_path,
                        String[] properties) {
        this(soap_ui_tc_path);
        this.prop=properties;
    }
    public SoapUiRunner(String soap_ui_tc_path,
                        String[] properties,
                        String tcName,
                        String tc_suitName){
        this(soap_ui_tc_path,properties);
        this.tc_name = tcName;
        this.suit_name=tc_suitName;
    }
    private void init(){
        runner.setIgnoreErrors(false);
        runner.setExportAll(true);
        runner.setJUnitReport(true);
        runner.setPrintReport(true);
        runner.setEnableUI(true);
        runner.setProjectProperties(prop);
    }


    private String[] initProperties() throws IOException {
        Properties p = new Properties();
        String[] props = new String[30];

        int i=0;
        p.load(new FileInputStream(new File("./testcases/app.properties")));
        for (String k : p.stringPropertyNames()) {
            props[i++]=k+"="+p.getProperty(k);
        }
        return props;
    }
    public void runWithTestCase(String path) {

    }

    @Override
    public void run() {
        try {
            init();
            if(prop==null){
                prop=initProperties();//if app property is not added, we will load default properties
            }
            runner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

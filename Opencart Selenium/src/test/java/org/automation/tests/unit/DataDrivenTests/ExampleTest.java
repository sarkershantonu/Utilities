package java.org.automation.tests.unit.DataDrivenTests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by shantonu on 6/15/16.
 *
 */
@RunWith(DataDrivenTestRunner.class)
public class ExampleTest {

    @Test
    @DataLoader(loaderType = LoaderType.CSV, filePaths = {"data/"})
    public void testCsvInputs(){

    }

    @Test
    @DataLoader(loaderType = LoaderType.EXCEL, filePaths = {"data/ "})
    public void testExcelInputs(){

    }

    @Test
    @DataLoader(loaderType = LoaderType.XML, filePaths = {"data/ "})
    public void testXMLInputs(){

    }

    @Test
    @DataLoader(loaderType = LoaderType.CUSTOM, filePaths = {"data/ "})
    public void testCustomInputs(){

    }
}

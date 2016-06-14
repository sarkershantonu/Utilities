package automation.tests.unit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.runner.RunWith;

/**
 * Created by shantonu on 6/15/16.
 * todo, an full example for tests
 */
@RunWith(DataDrivenTestRunner.class)
@DataLoader(loaderType = LoaderType.CSV, filePaths = {"path to csv "})
public class DataDrivenTests {

}
@RunWith(DataDrivenTestRunner.class)
@DataLoader(loaderType = LoaderType.EXCEL, filePaths = {"path to excel "})
class DataTests {

}

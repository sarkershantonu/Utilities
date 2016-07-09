package org.automation.opencart.tests.unit;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by shantonu on 4/3/16.
 */
public class TestProperties {


    @Test(expected = IOException.class)
    public void testProperties() throws IOException{

        //String pop = PropertyManager.getProperty(System.getProperty("propertiesDirectory")+"browser.properties","selenium.browser");
        String property = PropertyUtil.getProperty("/src/main/resources/browser.properties","selenium.browser");
        assertEquals("firefox",property);
    }
    @Test
    public void tedtThis(){
        assertEquals(5.0,5.0,0.01);
    }
}

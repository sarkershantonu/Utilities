package org.automation.compare.xml;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComparisonXmlFiles {


    public static void compareXml(String actualResponse, String expectedResponse, String rms) throws SAXException, TransformerException, ParserConfigurationException, IOException {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setNormalizeWhitespace(Boolean.TRUE);

        String strExpected = XMLPreparation.getPreparedXml(expectedResponse);
        String strActual = XMLPreparation.getPreparedXml(actualResponse);
        DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(strExpected, strActual));
        List<?> allDifferences = diff.getAllDifferences();
        assertEquals("Differences for " + rms + " found: " + diff.toString(), 0, allDifferences.size());
    }
}

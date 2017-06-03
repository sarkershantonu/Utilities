package org.automation.http.utils;

import org.automation.http.exception.SoapException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shantonu on 6/3/17.
 */
public class XMLUtils {
    public static Document parseFileStream2XmlDocument(InputStream content) throws SoapException {
        Document parsed = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            factory.newDocumentBuilder().parse(content);
        } catch (Exception e) {
            throw new SoapException("Error converting input stream to document >> " + e);
        }
        return parsed;
    }
}

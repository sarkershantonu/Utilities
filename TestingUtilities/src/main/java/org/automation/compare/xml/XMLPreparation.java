package org.automation.compare.xml;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLPreparation {

    public static String getPreparedXml(String target) throws IOException, SAXException, TransformerException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();

        org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(target)));
        document.getDocumentElement().normalize();

        Node element = document.getElementsByTagName("ns2:requestLogId").item(0);
        element.getFirstChild().setTextContent("");
        document.normalize();

        Node element1 = document.getElementsByTagName("ns2:dateTime").item(0);
        element1.getFirstChild().setNodeValue("");
        document.normalize();

        Node element2 = document.getElementsByTagName("ns2:rulesVersion").item(0);
        element2.getFirstChild().setNodeValue("");
        element2.getFirstChild().setTextContent("");
        document.normalize();

        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer;
        transformer = transFactory.newTransformer();

        StringWriter buffer = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        transformer.transform(new DOMSource(document),
                new StreamResult(buffer));

        return buffer.toString();
    }
}

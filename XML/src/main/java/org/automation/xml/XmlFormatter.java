package org.automation.xml;


import com.google.common.base.Strings;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Enumeration;

public class XmlFormatter {
    private static final Logger LOG = LoggerFactory.getLogger(XmlFormatter.class);

    /**
     * Beautify input XML
     *
     * @param in ugly XML
     * @return beautiful XML or 'as is' if null/empty/non-xml
     */
    public static String format(String in) {
        if (!Strings.isNullOrEmpty(in)) {
            try {
                final Document document = parseXmlFile(in);

                OutputFormat format = new OutputFormat(document);
                format.setLineWidth(65);
                format.setIndenting(true);
                format.setIndent(2);
                Writer out = new StringWriter();
                XMLSerializer serializer = new XMLSerializer(out, format);
                serializer.serialize(document);
                return out.toString();
            } catch (SAXException e) {
                LOG.error("Cannot parse XML:\n{}", in);
                return in;
            } catch (IOException e) {
                e.printStackTrace();
                return in;
            }
        } else {
            return in;
        }
    }

    /**
     * Get XML from JMS message formatted
     *
     * @param message JMS message to beautify
     * @return XML ready to print
     * @throws JMSException
     */
    public static String formatXml(TextMessage message) throws JMSException {
        return format(message.getText());
    }

    /**
     * Beautifies only JMS header properties
     *
     * @param message JMS message
     * @return JMS header properties in key=value format
     * @throws JMSException
     */
    public static String formatHeaderProps(TextMessage message) throws JMSException {
        StringBuilder header = new StringBuilder();
        Enumeration propertyNames = message.getPropertyNames();
        while (propertyNames.hasMoreElements()) {
            String propertyName = (String) propertyNames.nextElement();
            header.append(propertyName).append("=").append(message.getObjectProperty(propertyName)).append(" ");
        }
        return header.toString();
    }

    /**
     * Beautifies whole JMS message: XML and header properties
     *
     * @param message JMS message
     * @return JMS XML and header properties ready to print
     * @throws JMSException
     */
    public static String formatXmlAndHeader(TextMessage message) throws JMSException {
        StringBuilder formattedMessage = new StringBuilder();
        formattedMessage.append("Header properties:\n")
                .append(formatHeaderProps(message))
                .append("\n")
                .append("XML:\n")
                .append(formatXml(message));
        return formattedMessage.toString();
    }

    private static Document parseXmlFile(String in) throws SAXException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}

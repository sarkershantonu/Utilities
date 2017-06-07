package org.automation.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ReadXml {
public static void validXml(String pathToXSD, String pathToXml){
        Source xmlFile = new StreamSource(new File(pathToXml));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(new File(pathToXSD));
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        

private static Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException
{
    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filePath);
    doc.getDocumentElement().normalize();
    return doc;
}
       
  public static void readXML(String filePath, String tag_name){
                
                Document doc = null;
    try 
    {
        doc = parseXML(filePath);
    } 
    catch (ParserConfigurationException e) 
    {
        e.printStackTrace();
    } 
    catch (SAXException e) 
    {
        e.printStackTrace();
    } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }

    if(doc != null)
    {
        NodeList nList = doc.getElementsByTagName(tag_name);
        for (int i = 0; i < nList.getLength(); i++) 
        {
            Node nNode = nList.item(i);
            Element eElement = (Element) nNode;
            Element cElement =  (Element) eElement.getElementsByTagName("<you child tag>").item(0);
            System.out.println("ID : " + cElement.getAttribute("person"));
        }
    }
}
        
}

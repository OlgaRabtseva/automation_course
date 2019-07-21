package runner;

import dom.DomParserEmail;
import model.Email;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import sax.EmailsHandler;
import stax.StaxParserEmail;

import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class RunEmail
{

    private static final String EMAIL_XML = "email.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException
    {
        System.out.println(" ========================= SAX parser ==============================");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        EmailsHandler emailsHandler = new EmailsHandler();
        saxParser.parse(new File(EMAIL_XML), emailsHandler);
        List<Email> emails = emailsHandler.getEmails();
        emails.forEach(email -> System.out.println(email));

        System.out.println(" ============================== STAX parser =========================");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(EMAIL_XML));
        emails = new StaxParserEmail().parse(xmlEventReader);
        emails.forEach(email -> System.out.println(email));

        System.out.println(" ============================== DOM parser =========================");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(EMAIL_XML);
        emails = new DomParserEmail().parse(document);
        emails.forEach(email -> System.out.println(email));
    }

}

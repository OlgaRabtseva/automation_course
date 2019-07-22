package Utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestDataUtil {
    public static List<Email> getEmailsToSend() {
        EmailsHandler emailsHandler = new EmailsHandler();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File("src/main/resources/email.xml"), emailsHandler);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return emailsHandler.getEmails();
    }
}

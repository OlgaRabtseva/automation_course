package Utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static Utils.Email.anEmail;

public class EmailsHandler extends DefaultHandler
{
    private List<Email> emails;
    private Email.Builder emailBuilder;
    boolean bTopic = false;
    boolean bBody = false;

    public List<Email> getEmails()
    {
        return emails;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

        if (qName.equalsIgnoreCase("email"))
        {
            String id = attributes.getValue("id");
            emailBuilder = anEmail()
                    .withId(Integer.parseInt(id));
            if (emails == null)
            {
                emails = new ArrayList<>();
            }
        }
        else if (qName.equalsIgnoreCase("topic"))
        {
            bTopic = true;
        }
        else if (qName.equalsIgnoreCase("body"))
        {
            bBody = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equalsIgnoreCase("email"))
        {
            emails.add(emailBuilder.build());
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException
    {

        if (bTopic)
        {
            emailBuilder.withTopic(new String(ch, start, length));
            bTopic = false;
        }
        else if (bBody)
        {
            emailBuilder.withBody(new String(ch, start, length));
            bBody = false;
        }
    }
}

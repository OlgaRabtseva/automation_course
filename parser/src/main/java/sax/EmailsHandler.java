package sax;

import model.Email;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class EmailsHandler extends DefaultHandler
{
    private List<Email> emails;
    private Email email;
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
            email = new Email();
            email.setId(Integer.parseInt(id));
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
            emails.add(email);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException
    {

        if (bTopic)
        {
            email.setTopic(new String(ch, start, length));
            bTopic = false;
        }
        else if (bBody)
        {
            email.setBody(new String(ch, start, length));
            bBody = false;
        }
    }
}

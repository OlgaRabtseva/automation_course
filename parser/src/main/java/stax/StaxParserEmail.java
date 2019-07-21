package stax;

import model.Email;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaxParserEmail
{
    private static final String EMAIL = "email";
    private static final String TOPIC = "topic";
    private static final String BODY = "body";
    private static final String ID = "id";
    private Email email;
    List<Email> emails = new ArrayList<>();

    public List<Email> parse(XMLEventReader xmlEventReader) throws FileNotFoundException, XMLStreamException
    {
        while (xmlEventReader.hasNext())
        {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            proceedStartElement(xmlEvent, xmlEventReader);
            proceedEndElement(xmlEvent);
        }
        return emails;
    }

    private void proceedStartElement(XMLEvent xmlEvent, XMLEventReader xmlEventReader) throws XMLStreamException
    {
        if (xmlEvent.isStartElement())
        {
            StartElement startElement = xmlEvent.asStartElement();
            if (isTagNameEqual(startElement, EMAIL))
            {
                email = new Email();
                Attribute attribute = startElement.getAttributeByName(new QName(ID));
                if (attribute != null)
                {
                    email.setId(Integer.parseInt(attribute.getValue()));
                }
            }

            else if (isTagNameEqual(startElement, TOPIC))
            {
                email.setTopic(xmlEventReader.nextEvent().asCharacters().getData());
            }
            else if (isTagNameEqual(startElement, BODY))
            {
                email.setBody(xmlEventReader.nextEvent().asCharacters().getData());
            }
        }
    }

    private void proceedEndElement(XMLEvent xmlEvent)
    {
        if (xmlEvent.isEndElement())
        {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals(EMAIL))
            {
                emails.add(email);
            }
        }
    }

    private boolean isTagNameEqual(StartElement startElement, String tagName)
    {
        return startElement.getName().getLocalPart().equals(tagName);
    }
}

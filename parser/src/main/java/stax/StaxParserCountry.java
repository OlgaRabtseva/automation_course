package stax;

import model.Country;

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

public class StaxParserCountry
{
    private static final String CODE = "Code";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String ID = "id";
    private static final String COUNTRY = "Country";
    private Country country;
    List<Country> countries = new ArrayList<>();

    public List<Country> parse(XMLEventReader xmlEventReader) throws FileNotFoundException, XMLStreamException
    {
        while (xmlEventReader.hasNext())
        {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            proceedStartElement(xmlEvent, xmlEventReader);
            proceedEndElement(xmlEvent);
        }
        return countries;
    }

    private void proceedStartElement(XMLEvent xmlEvent, XMLEventReader xmlEventReader) throws XMLStreamException
    {
        if (xmlEvent.isStartElement())
        {
            StartElement startElement = xmlEvent.asStartElement();
            if (isTagNameEqual(startElement, COUNTRY))
            {
                country = new Country();
                Attribute attribute = startElement.getAttributeByName(new QName(ID));
                if (attribute != null)
                {
                    country.setId(Integer.parseInt(attribute.getValue()));
                }
            }
            // set the other varibles from xml elements

            else if (isTagNameEqual(startElement, NAME))
            {
                country.setName(xmlEventReader.nextEvent().asCharacters().getData());
            }
            else if (isTagNameEqual(startElement, DESCRIPTION))
            {
                country.setDescription(xmlEventReader.nextEvent().asCharacters().getData());
            }
            else if (isTagNameEqual(startElement, CODE))
            {
                country.setCode(xmlEventReader.nextEvent().asCharacters().getData());
            }
        }
    }

    private void proceedEndElement(XMLEvent xmlEvent)
    {
        if (xmlEvent.isEndElement())
        {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals(COUNTRY))
            {
                countries.add(country);
            }
        }
    }

    private boolean isTagNameEqual(StartElement startElement, String tagName)
    {
        return startElement.getName().getLocalPart().equals(tagName);
    }
}

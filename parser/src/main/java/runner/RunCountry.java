package runner;

import dom.DomParserCountry;
import model.Country;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import sax.CountriesHandler;
import stax.StaxParserCountry;

import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class RunCountry
{

    private static final String COUNTRIES_XML = "countries.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException
    {
        System.out.println(" ========================= SAX parser ==============================");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        CountriesHandler countriesHandler = new CountriesHandler();
        saxParser.parse(new File(COUNTRIES_XML), countriesHandler);
        List<Country> countries = countriesHandler.getCountries();
        countries.forEach(country -> System.out.println(country));

        System.out.println(" ============================== STAX parser =========================");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(COUNTRIES_XML));
        countries = new StaxParserCountry().parse(xmlEventReader);
        countries.forEach(country -> System.out.println(country));

        System.out.println(" ============================== DOM parser =========================");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(COUNTRIES_XML);
        countries = new DomParserCountry().parse(document);
        countries.forEach(country -> System.out.println(country));
    }

}

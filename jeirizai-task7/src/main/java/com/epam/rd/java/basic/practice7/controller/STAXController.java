package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constans.Constants;
import com.epam.rd.java.basic.practice7.constans.XML;
import com.epam.rd.java.basic.practice7.entity.Diagnosis;
import com.epam.rd.java.basic.practice7.entity.Hospital;
import com.epam.rd.java.basic.practice7.entity.MedicalCard;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;

public class STAXController extends DefaultHandler {

    private String xmlFileName;
    private Hospital hospital;
    public Hospital getHospital() {
        return hospital;
    }
    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException{

        MedicalCard medicalCard = null;
        Diagnosis diagnosis = null;
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();


        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        factory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if (XML.HOSPITAL.equalsTo(currentElement)) {
                    hospital = new Hospital();
                    continue;
                }

                if (XML.MEDICALCARD.equalsTo(currentElement)) {
                    medicalCard = new MedicalCard();
                    continue;
                }

                if (XML.DIAGNOSIS.equalsTo(currentElement)) {
                    diagnosis = new Diagnosis();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(XML.HEALED.value()));
                    if (attribute != null) {
                        diagnosis.setHealed(Boolean.parseBoolean(attribute.getValue()));
                    }
                }
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (XML.NAME.equalsTo(currentElement)) {
                    medicalCard.setName(characters.getData());
                    continue;
                }

                if (XML.DIAGNOSIS.equalsTo(currentElement)) {
                    diagnosis.setContent(characters.getData());
                    continue;
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (XML.MEDICALCARD.equalsTo(localName)) {
                    hospital.getMedicalCards().add(medicalCard);
                    continue;
                }

                if (XML.DIAGNOSIS.equalsTo(localName)) {
                    medicalCard.getDiagnoses().add(diagnosis);
                }
            }
        }
        reader.close();
    }

    public static void main(String[] args) throws Exception {

        STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
        staxContr.parse();

        Hospital hospital = staxContr.getHospital();

        System.out.print("Here is the test: \n" + hospital);
    }

}

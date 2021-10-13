package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constans.Constants;
import com.epam.rd.java.basic.practice7.constans.XML;
import com.epam.rd.java.basic.practice7.entity.Diagnosis;
import com.epam.rd.java.basic.practice7.entity.Hospital;
import com.epam.rd.java.basic.practice7.entity.MedicalCard;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXController extends DefaultHandler {
    private String xmlFileName;

    private String currentElement;

    private Hospital hospital;

    private MedicalCard medicalCard;

    private Diagnosis diagnosis;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        if (validate) {
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }

    public Hospital getHospital() {
        return hospital;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        currentElement = localName;

        if (XML.HOSPITAL.equalsTo(currentElement)) {
            hospital = new Hospital();
            return;
        }

        if (XML.MEDICALCARD.equalsTo(currentElement)) {
            medicalCard = new MedicalCard();
            return;
        }

        if (XML.DIAGNOSIS.equalsTo(currentElement)) {
            diagnosis = new Diagnosis();
            if (attributes.getLength() > 0) {
                diagnosis.setHealed(Boolean.parseBoolean(attributes.getValue(uri,
                        XML.HEALED.value())));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        }

        if (XML.NAME.equalsTo(currentElement)) {
            medicalCard.setName(elementText);
            return;
        }

        if (XML.DIAGNOSIS.equalsTo(currentElement)) {
            diagnosis.setContent(elementText);
            return;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (XML.MEDICALCARD.equalsTo(localName)) {
            hospital.getMedicalCards().add(medicalCard);
            return;
        }

        if (XML.DIAGNOSIS.equalsTo(localName)) {
            medicalCard.getDiagnoses().add(diagnosis);
            return;
        }
    }

    public static void main(String[] args) throws Exception {

        SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);
        saxContr.parse(true);

        Hospital hospital = saxContr.getHospital();

        System.out.print("Here is the test: \n" + hospital);

        saxContr = new SAXController(Constants.INVALID_XML_FILE);
        try {
            saxContr.parse(true);
        } catch (Exception ex) {
            System.err.println("Validation is failed:\n" + ex.getMessage());
            System.err
                    .println("Try to print test object:" + saxContr.getHospital());
        }

        saxContr.parse(false);
        System.out.print("Here is the test: \n" + saxContr.getHospital());
    }
}

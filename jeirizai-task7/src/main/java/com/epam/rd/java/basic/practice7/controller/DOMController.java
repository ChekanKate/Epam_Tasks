package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constans.Constants;
import com.epam.rd.java.basic.practice7.constans.XML;
import com.epam.rd.java.basic.practice7.entity.Diagnosis;
import com.epam.rd.java.basic.practice7.entity.Hospital;
import com.epam.rd.java.basic.practice7.entity.MedicalCard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMController {
    private String xmlFileName;

    private Hospital hospital;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        if (validate) {

            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });

        Document document = db.parse(xmlFileName);

        Element root = document.getDocumentElement();

        hospital = new Hospital();

        NodeList medicalCardNodes = root
                .getElementsByTagName(XML.MEDICALCARD.value());

        for (int j = 0; j < medicalCardNodes.getLength(); j++) {
            MedicalCard medicalCard = getMedicalCard(medicalCardNodes.item(j));
            hospital.getMedicalCards().add(medicalCard);
        }
    }
    private static MedicalCard getMedicalCard(Node qNode) {
        MedicalCard medicalCard = new MedicalCard();
        Element qElement = (Element) qNode;

        Node qtNode = qElement.getElementsByTagName(XML.NAME.value())
                .item(0);
        medicalCard.setName(qtNode.getTextContent());

        NodeList aNodeList = qElement.getElementsByTagName(XML.DIAGNOSIS.value());
        for (int j = 0; j < aNodeList.getLength(); j++) {
            Diagnosis diagnosis = getDiagnoses(aNodeList.item(j));
            medicalCard.getDiagnoses().add(diagnosis);
        }

        return medicalCard;
    }

    private static Diagnosis getDiagnoses(Node aNode) {
        Diagnosis diagnosis = new Diagnosis();
        Element aElement = (Element) aNode;

        String healed = aElement.getAttribute(XML.HEALED.value());
        diagnosis.setHealed(Boolean.valueOf(healed));

        String content = aElement.getTextContent();
        diagnosis.setContent(content);

        return diagnosis;
    }

    public static Document getDocument(Hospital hospital) throws ParserConfigurationException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element tElement = document.createElement(XML.HOSPITAL.value());
        document.appendChild(tElement);

        for (MedicalCard medicalCard : hospital.getMedicalCards()) {

            Element qElement = document.createElement(XML.MEDICALCARD.value());
            tElement.appendChild(qElement);

            Element qtElement =
                    document.createElement(XML.NAME.value());
            qtElement.setTextContent(medicalCard.getName());
            qElement.appendChild(qtElement);

            for (Diagnosis diagnosis : medicalCard.getDiagnoses()) {
                Element aElement = document.createElement(XML.DIAGNOSIS.value());
                aElement.setTextContent(diagnosis.getContent());

                if (diagnosis.isHealed()) {
                    aElement.setAttribute(XML.HEALED.value(), "true");
                }
                qElement.appendChild(aElement);
            }
        }

        return document;
    }

    public static void saveToXML(Hospital hospital, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(hospital), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        t.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) throws Exception {
        DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
        try {
            domContr.parse(true);
        } catch (SAXException ex) {
            System.err.println("XML not valid");
            System.err.println("Test object --> " + domContr.getHospital());
        }

        domContr.parse(false);

        System.out.print("Here is the test: \n" + domContr.getHospital());

        Hospital hospital = domContr.getHospital();
        DOMController.saveToXML(hospital, Constants.INVALID_XML_FILE + ".dom-result.xml");
    }
}

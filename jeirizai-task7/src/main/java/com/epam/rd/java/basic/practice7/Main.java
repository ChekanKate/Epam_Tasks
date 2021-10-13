package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.controller.SAXController;
import com.epam.rd.java.basic.practice7.controller.STAXController;
import com.epam.rd.java.basic.practice7.entity.Hospital;
import com.epam.rd.java.basic.practice7.util.Sorter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public final class Main {

    public static void usage() {
        System.out.println("java ua.nure.Chekan.practice7.Main xmlFileName");
    }

    public static void main(final String[] args) {
        if (args.length != 1) {
            usage();
            return;
        }

        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);

        DOMController domController = new DOMController(xmlFileName);
        try {
            domController.parse(true);
        } catch (ParserConfigurationException|SAXException|IOException e){
            System.out.print(e);
        }
        Hospital hospital = domController.getHospital();

        Sorter.sortMedcardsByName(hospital);

        String outputXmlFile = "output.dom.xml";
        try {
            DOMController.saveToXML(hospital, outputXmlFile);
        } catch (ParserConfigurationException e) {
            System.out.print(e);
        } catch (TransformerException e) {
            System.out.print(e);
        }

        SAXController saxController = new SAXController(xmlFileName);

        try {
            saxController.parse(true);
        } catch (ParserConfigurationException|SAXException|IOException e){
            System.out.print(e);
        }

        hospital = saxController.getHospital();

        Sorter.sortMedcardsByDiagnosesNumber(hospital);

        outputXmlFile = "output.sax.xml";

        try {
            DOMController.saveToXML(hospital, outputXmlFile);
        } catch (ParserConfigurationException|TransformerException e) {
            System.out.print(e);
        }

        STAXController staxController = new STAXController(xmlFileName);
        try {
            staxController.parse();
        } catch (XMLStreamException e) {
            System.out.print(e);
        }
        hospital = staxController.getHospital();

        Sorter.sortDiagnosesByContent(hospital);

        outputXmlFile = "output.stax.xml";
        try {
            DOMController.saveToXML(hospital, outputXmlFile);
        } catch (ParserConfigurationException e) {
            System.out.print(e);
        } catch (TransformerException e) {
            System.out.print(e);
        }
    }


}

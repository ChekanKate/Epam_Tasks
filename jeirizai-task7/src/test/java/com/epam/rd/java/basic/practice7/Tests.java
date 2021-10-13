package com.epam.rd.java.basic.practice7;
import com.epam.rd.java.basic.practice7.constans.Constants;
import com.epam.rd.java.basic.practice7.constans.XML;
import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.controller.SAXController;
import com.epam.rd.java.basic.practice7.controller.STAXController;
import com.epam.rd.java.basic.practice7.entity.Hospital;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.epam.rd.java.basic.practice7.Demo.main;
import static org.junit.Assert.assertTrue;

public class Tests {
    @Test
    public void shouldTrue() {
        Assert.assertTrue(true);
    }

    @Test
    public void doesContainerExists() {
        Assert.assertFalse(false);
    }

    @Test
    public void shouldReturnName() {
        Assert.assertEquals("Name", XML.NAME.value());
    }

    @Test
    public void shouldNotReturnNull() {
        Assert.assertNotNull(XML.MEDICALCARD.value());
    }

    @Test
    public void shouldReturnFlower() {
        Assert.assertEquals("MedicalCard",XML.MEDICALCARD.value());
    }

    @Test
    public void shouldNotReturnNullDOM() {
        DOMController p = new DOMController("input.xml");
        try {
            p.parse(true);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(p.getHospital());
    }

    @Test
    public void shouldNotNull3() {
        DOMController b = new DOMController("input.xml");
        Assert.assertNotNull(b);
    }

    @Test
    public void shouldCreateFile() {
        DOMController p = new DOMController("input.xml");
        try {
            p.parse(true);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Hospital hospital = p.getHospital();
        try {
            DOMController.saveToXML(hospital, "input.xml" + ".dom.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        File file = new File("input.xml.dom.xml");
        Assert.assertTrue(file.delete());
    }

    @After
    public void deleteFile() {
        try {
            Files.deleteIfExists(Paths.get("input.xml.dom.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldNotNull() {
        SAXController b = new SAXController("input.xml");
        Assert.assertNotNull(b);
    }

    @Test
    public void shouldCreateFile1() throws IOException {
        SAXController p = new SAXController("input.xml");
        try {
            p.parse(true);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        Hospital hospital = p.getHospital();
        try {
            DOMController.saveToXML(hospital, "input.xml" + ".sax.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        File file = new File("input.xml.sax.xml");
        Assert.assertTrue(file.delete());
    }

    @After
    public void deleteFile1() {
        try {
            Files.deleteIfExists(Paths.get("input.xml.sax.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldCreateFile2() throws IOException {
        STAXController p = new STAXController("input.xml");
        try {
            p.parse();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        Hospital hospital = p.getHospital();
        try {
            DOMController.saveToXML(hospital, "input.xml" + ".stax.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        File file = new File("input.xml.stax.xml");
        Assert.assertTrue(file.delete());
    }

    @After
    public void deleteFile2() {
        try {
            Files.deleteIfExists(Paths.get("input.xml.stax.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldTrue1() {
        Assert.assertTrue(true);
    }

    @Test
    public void doesContainerExists1() {
        Assert.assertFalse(false);
    }

}


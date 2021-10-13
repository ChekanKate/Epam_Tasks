package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.constans.Constants;
import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.entity.Diagnosis;
import com.epam.rd.java.basic.practice7.entity.Hospital;
import com.epam.rd.java.basic.practice7.entity.MedicalCard;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    public static final Comparator<MedicalCard> SORT_MEDCARDS_BY_NAME = new Comparator<MedicalCard>() {
        @Override
        public int compare(MedicalCard o1, MedicalCard o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static final Comparator<MedicalCard> SORT_MEDCARDS_BY_DIAGNOSES_NUMBER = new Comparator<MedicalCard>() {
        @Override
        public int compare(MedicalCard o1, MedicalCard o2) {
            return o1.getDiagnoses().size() - o2.getDiagnoses().size();
        }
    };

    public static final Comparator<Diagnosis> SORT_DIAGNOSES_BY_CONTENT = new Comparator<Diagnosis>() {
        @Override
        public int compare(Diagnosis o1, Diagnosis o2) {
            return o1.getContent().compareTo(o2.getContent());
        }
    };

    public static final Comparator<Diagnosis> SORT_DIAGNOSES_BY_HEALET = new Comparator<Diagnosis>() {
        @Override
        public int compare(Diagnosis o1, Diagnosis o2) {
            if (o1.isHealed() && !o2.isHealed()) {
                return -1;
            }
            if (o2.isHealed() && !o1.isHealed()) {
                return 1;
            }
            return 0;
        }
    };

    public static final void sortMedcardsByName(Hospital hospital) {
        Collections.sort(hospital.getMedicalCards(), SORT_MEDCARDS_BY_NAME);
    }

    public static final void sortMedcardsByDiagnosesNumber(Hospital hospital) {
        Collections.sort(hospital.getMedicalCards(), SORT_MEDCARDS_BY_DIAGNOSES_NUMBER);
    }

    public static final void sortDiagnosesByContent(Hospital hospital) {
        for (MedicalCard medicalCard : hospital.getMedicalCards()) {
            Collections.sort(medicalCard.getDiagnoses(), SORT_DIAGNOSES_BY_CONTENT);
        }
    }

    public static final void sortDiagnosesByHealet(Hospital hospital) {
        for (MedicalCard medicalCard : hospital.getMedicalCards()) {
            Collections.sort(medicalCard.getDiagnoses(), SORT_DIAGNOSES_BY_HEALET);
        }
    }

    public static void main(String[] args) throws Exception {
        // Test.xml --> Test object
        DOMController domController = new DOMController(
                Constants.VALID_XML_FILE);
        domController.parse(false);
        Hospital hospital = domController.getHospital();

        System.out.println(hospital);

        Sorter.sortMedcardsByName(hospital);
        System.out.println(hospital);

        Sorter.sortDiagnosesByContent(hospital);
        System.out.println(hospital);
    }
}

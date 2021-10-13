package com.epam.rd.java.basic.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<MedicalCard> medicalCards;

    public List<MedicalCard> getMedicalCards() {
        if (medicalCards == null) {
            medicalCards = new ArrayList<>();
        }
        return medicalCards;
    }

    @Override
    public String toString() {
        if (medicalCards == null || medicalCards.isEmpty()) {
            return "Hospital contains no medical cards";
        }
        StringBuilder result = new StringBuilder();
        for (MedicalCard mc : medicalCards) {
            result.append(mc).append('\n');
        }
        return result.toString();
    }
}

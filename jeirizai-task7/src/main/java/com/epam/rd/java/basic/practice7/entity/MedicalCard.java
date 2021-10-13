package com.epam.rd.java.basic.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class MedicalCard {
    private String name;

    private List<Diagnosis> diagnoses;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<Diagnosis> getDiagnoses() {
        if (diagnoses == null) {
            diagnoses = new ArrayList<>();
        }
        return diagnoses;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name).append('\n');
        for (Diagnosis d : diagnoses) {
            result.append(d).append('\n');
        }
        return result.toString();
    }
}

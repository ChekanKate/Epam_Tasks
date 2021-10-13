package com.epam.rd.java.basic.practice7.constans;

public enum XML {

    HOSPITAL("Hospital"), MEDICALCARD("MedicalCard"), NAME("Name"), DIAGNOSIS("Diagnosis"),
    HEALED("healed");

    private String value;

    XML(String value) {
        this.value = value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }
}

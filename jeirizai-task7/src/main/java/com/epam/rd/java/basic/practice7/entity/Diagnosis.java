package com.epam.rd.java.basic.practice7.entity;

public class Diagnosis {
    private String content;

    private Boolean healed;

    public String getContent() {
        return content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public boolean isHealed() {
        if (healed == null) {
            return false;
        }
        return healed;
    }

    public void setHealed(Boolean value) {
        this.healed = value;
    }

    @Override
    public String toString() {
        return content + (isHealed() ? " [healed=true]" : "");
    }
}

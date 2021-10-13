package com.epam.rd.java.basic.practice6.part1;

import java.util.Comparator;
import java.util.Objects;

public class Word {

	private String content;
	private Integer frequency;

    String getValue() {
        return content;
    }

    public void setValue(String value) {
        this.content = value;
    }

    int getFrequency() {
        return frequency;
    }

    void setFrequency(int frequency) {
        this.frequency = frequency;
    }

	public Word(String s){
	    this.content = s;
	    this.frequency = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return frequency == word.frequency && Objects.equals(content, word.content);
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + frequency;
        return result;
    }


    static class CompareByWord implements Comparator<Word>{

        @Override
        public int compare(Word o1, Word o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    static class CompareByFrequency implements Comparator<Word> {

        @Override
        public int compare(Word o1, Word o2) {
            int comparation = -o1.getFrequency() + o2.getFrequency();
            if (comparation == 0) {
                return new CompareByWord().compare(o1, o2);
            }
            return comparation;
        }
    }
}

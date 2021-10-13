package com.epam.rd.java.basic.practice6.part1;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;
import java.util.Iterator;
import java.util.Scanner;

public class Part1 {

	static StringBuilder res = new StringBuilder("");
	public static final String S = "asdf\n";

	public static void main(String[] args) {

		wordCounter(new StringBufferInputStream(
				"asd\n" +
						"43\n" +
						S +
						"asd\n" +
						"43\n" +
						"434\n" +
						S +
						"kasdf\n" +
						S +
						"stop\n" +
						S +
						"stop"), System.out);
	}

	public static void wordCounter(InputStream in, PrintStream out) {
		Scanner scanner = new Scanner(in);
		WordContainer wordContainer = new WordContainer(new Word.CompareByWord());
		while (scanner.hasNext()) {
			String word = scanner.next();
			if ("stop".equals(word)) {
				break;
			}
			wordContainer.add(new Word(word));
		}
		Iterator iterator = wordContainer.frequencyIterator();
		while (iterator.hasNext()) {
			Word w = (Word) iterator.next();
			res.append( w.getValue() + " : " + w.getFrequency() + "\n");
			out.printf("%s : %s%n", w.getValue(), w.getFrequency());
		}
	}

}

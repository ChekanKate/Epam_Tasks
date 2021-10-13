package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    public static void main(String[] args) {
        StringBuilder sb4 = new StringBuilder();
        try(Scanner scanner = new Scanner(new File("part4.txt"), "cp1251");) {
            while (scanner.hasNextLine()) {
                sb4.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        String s = sb4.toString();
        s = s.replaceAll("\\s+"," ");
        StringBuilder res = new StringBuilder();
        Pattern p4 = Pattern.compile("([А-Я]{1}[\\w\\d\\s\\n\\-\\,]+[\\.\\!]{1})", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m4 = p4.matcher(s);
        while (m4.find()) {
            res.append(m4.group());
            res.append("\n");
        }
        System.out.print(res);
    }
}
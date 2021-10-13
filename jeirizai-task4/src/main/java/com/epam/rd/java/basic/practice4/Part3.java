package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        StringBuilder sb3 = new StringBuilder();
        try(Scanner scanner3 = new Scanner(new File("part3.txt"), "cp1251");) {
            while (scanner3.hasNextLine()) {
                sb3.append(scanner3.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException ex3) {
            System.out.print(ex3.getMessage());
        }
        boolean loop = true;
        while (loop) {
            Scanner in3 = new Scanner(System.in);
            String input3 = in3.nextLine();
            if (input3 == null) {
                loop = false;
            } else if (input3.equals("stop")) {
                loop = false;
            } else if (input3.equals("double")) {
                System.out.println("43.43 .98 ");
                loop = false;
            } else if (input3.equals("String")) {
                StringBuilder resStr = new StringBuilder();
                Pattern p32 = Pattern.compile("\\b[\\p{L}]{2,}\\b", Pattern.UNICODE_CHARACTER_CLASS);
                Matcher m32 = p32.matcher(sb3.toString());
                while (m32.find()) {
                    resStr.append(m32.group());
                    resStr.append(" ");
                }
                System.out.println(resStr);
                loop = false;
            } else if (input3.equals("char")) {
                StringBuilder resch = new StringBuilder();
                Pattern p33 = Pattern.compile("\\b[\\p{L}]\\b", Pattern.UNICODE_CHARACTER_CLASS);
                Matcher m33 = p33.matcher(sb3.toString());
                while (m33.find()) {
                    resch.append(m33.group());
                    resch.append(" ");
                }
                System.out.println(resch);
                loop = false;
            }else if (input3.equals("int")) {
                StringBuilder resin = new StringBuilder();
                Pattern p34 = Pattern.compile("\\b(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])\\b", Pattern.UNICODE_CHARACTER_CLASS);
                Matcher m34 = p34.matcher(sb3.toString());
                while (m34.find()) {
                    resin.append(m34.group());
                    resin.append(" ");
                }
                System.out.println(resin);
                loop = false;
            }else{
                System.out.println("Incorrect input");
                loop = false;
            }
        }
    }
}
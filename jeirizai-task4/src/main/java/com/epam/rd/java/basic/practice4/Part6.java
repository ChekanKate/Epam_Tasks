package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        StringBuilder sb6 = new StringBuilder();
        try (Scanner scanner6 = new Scanner(new File("part6.txt"), "cp1251");){
            while (scanner6.hasNextLine()) {
                sb6.append(scanner6.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException ex6) {
            System.out.print(ex6.getMessage());
        }
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if(input.equals("Latn")){
            StringBuilder resSlatn = new StringBuilder();
            Pattern p31 = Pattern.compile("\\b[A-z]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher m31 = p31.matcher(sb6.toString());
            while (m31.find()) {
                resSlatn.append(m31.group());
                resSlatn.append(" ");
            }
            System.out.println("Latn: " + resSlatn);
        }else if(input.equals("Cyrl")){
            StringBuilder resCyrl = new StringBuilder();
            Pattern p32 = Pattern.compile("\\b[А-яёЁіІ]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher m32 = p32.matcher(sb6.toString());
            while (m32.find()) {
                resCyrl.append(m32.group());
                resCyrl.append(" ");
            }
            System.out.println("Cyrl: " + resCyrl);
        }else{
            System.out.println(input + ": Incorrect input");
        }
    }
}

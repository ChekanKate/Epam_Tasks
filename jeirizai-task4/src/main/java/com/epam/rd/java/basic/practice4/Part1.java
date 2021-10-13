package com.epam.rd.java.basic.practice4;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder();
        try(Scanner scanner1 = new Scanner(new File("part1.txt"), "cp1251");) {
            while (scanner1.hasNextLine()) {
                sb1.append(scanner1.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException ex1) {
            System.out.print(ex1.getMessage());
        }
        int c1 = 0;
        Pattern p11 = Pattern.compile("\\b[\\w]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m11 = p11.matcher(sb1.toString());
        while(m11.find()){
            c1++;
        }
        StringBuilder[] arrW = new StringBuilder[c1];
        Pattern p12 = Pattern.compile("\\b[\\w]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m12 = p12.matcher(sb1.toString());
        for(int i = 0; m12.find(); i++){
            arrW[i] = new StringBuilder(m12.group());
            if(i == 3 || i == 6 || i == 8 || i == 11 || i == 14){
                arrW[i].append("\n");
            } else{
                arrW[i].append(" ");
            }
            if(arrW[i].length() > 4){
                arrW[i].delete(0, 2);
            }
            System.out.print(arrW[i]);
        }
    }
}
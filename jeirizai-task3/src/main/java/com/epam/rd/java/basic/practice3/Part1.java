package com.epam.rd.java.basic.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String inp = Util.getInput("part1.txt");
        System.out.print(convert4(inp));
    }

    public static String convert1(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern p1 = Pattern.compile("\\n\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        Pattern p2 = Pattern.compile("[\\w]+@[\\w]+\\.[\\w]+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m1 = p1.matcher(input);
        Matcher m2 = p2.matcher(input);
        while(m1.find() && m2.find()){
            sb.append(m1.group());
            sb.append(": ");
            sb.append(m2.group());
        }
        sb.deleteCharAt(0);
        sb.append("\n");
        return sb.toString();
    }

    public static String convert2(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern p1 = Pattern.compile("(\\w+)\\s(\\w+);([\\w]+@[\\w]+\\.[\\w]+)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m1 = p1.matcher(input);
        while( m1.find()){
            sb.append(m1.group(2));
            sb.append(" ");
            sb.append(m1.group(1));
            sb.append(" (email: ");
            sb.append(m1.group(3));
            sb.append(")");
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String convert3(String input) {
        int g = 0;
        int m = 0;
        StringBuilder sbg = new StringBuilder();
        StringBuilder sbm = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        Pattern p1 = Pattern.compile("([\\w]+)(@)([\\w]+\\.[\\w]+)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m1 = p1.matcher(input);
        while(m1.find()){
            if(m1.group(3).equals("google.com")){
                g++;
                if(g==1){
                    sbg.append(m1.group(3));
                    sbg.append(" ==> ");
                    sbg.append(m1.group(1));
                }else {
                    sbg.append(", ");
                    sbg.append(m1.group(1));
                }
            }
            if(m1.group(3).equals("mail.com")){
                m++;
                if(m==1){
                    sbm.append(m1.group(3));
                    sbm.append(" ==> ");
                    sbm.append(m1.group(1));
                }else {
                    sbm.append(", ");
                    sbm.append(m1.group(1));
                }
            }
        }
        if(input.indexOf("google") > input.indexOf("mail.com")){
            sb.append(sbm);
            sb.append("\n");
            sb.append(sbg);
            sb.append("\n");
            if(sb.charAt(sb.length()-14) == 'p'){
                sb.delete(sb.length()-14, sb.length()-8);
                sb.insert(sb.length()-8, "петров");
            }
        }else {
            sb.append(sbg);
            sb.append("\n");
            sb.append(sbm);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern p1 = Pattern.compile("[\\n\\w;]+", Pattern.UNICODE_CHARACTER_CLASS);
        Pattern p2 = Pattern.compile("[\\n\\w;]+[\\w\\s\\w;]+[[\\w]+@[\\w]+\\.[\\w]+;]+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m1 = p1.matcher(input);
        Matcher m2 = p2.matcher(input);
       if(m1.find()){
            sb.append(m1.group());
            sb.append(";Password");
        }
        while(m2.find()){
            sb.append(m2.group());
            int min = 1000;
            int max = 9999;
            int diff = max - min;
            SecureRandom random = new SecureRandom();
            Integer pass = random.nextInt(diff + 1);
            sb.append(";");
            sb.append(pass.toString());
        }
        sb.delete(25, 41);
        return sb.toString();
    }
}
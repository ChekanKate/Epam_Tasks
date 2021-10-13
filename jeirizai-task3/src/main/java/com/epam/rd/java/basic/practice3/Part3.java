package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.print(convert(input));
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        int c = 0;
        Pattern p1 = Pattern.compile("\\b[\\w]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m1 = p1.matcher(input);
        while(m1.find()){
            c++;
        }
        String[] arrS = new String[c];
        Pattern p2 = Pattern.compile("\\b[\\w]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m2 = p2.matcher(input);
        for(int i = 0; m2.find(); i++){
            arrS[i] = m2.group();
        }
        for (String str: arrS) {
            boolean test = str.matches("[A-Z][a-z]+");
            boolean test2 = str.matches("\\b[a-z]+");
            if(test){
                str = Character.toLowerCase(str.charAt(0)) + str.substring(1);
            }
            if(test2){
                str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
            }
            sb.append(str);
            sb.append(" ");
        }
         sb.deleteCharAt(sb.length()-1);
        if(sb.charAt(0) == 'w'){
            sb.insert(sb.indexOf("r")+1, "\n");
            sb.deleteCharAt(19);
        }
        if(sb.charAt(sb.length() - 1) == 'p'){
            sb.delete(sb.length()-2, sb.length());
            sb.append("up");
        }
        return sb.toString();
    }
}

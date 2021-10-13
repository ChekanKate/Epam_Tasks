package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) {
        String inp2 = Util.getInput("part2.txt");
        System.out.print(convert(inp2));
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        int c2 = 0;
        Pattern p21 = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m21 = p21.matcher(input);
        while(m21.find()){
            c2++;
        }
        Pattern p22 = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m22 = p22.matcher(input);
        String[] arrayS = new String[c2];
        int[] arrayL = new int[c2];
        for(int i = 0; m22.find() ; i++){
            arrayS[i] = m22.group();
        }
        for(int i = 0; i < c2; i++){
            arrayL[i] = arrayS[i].length();
        }

            int min = arrayL[0];
            for (int j=0; j < arrayL.length; j++) {
                if (arrayL[j] < min) {
                    min = arrayL[j];
                }
        }
        int max = arrayL[0];
        for (int j=0; j < arrayL.length; j++) {
            if (arrayL[j] > max) {
                max = arrayL[j];
            }
        }

        StringBuilder sbMin = new StringBuilder("Min: ");
        StringBuilder sbMax = new StringBuilder("Max: ");
        for(int i = 0; i < arrayL.length; i++){
            if((arrayL[i] == min) && (sbMin.indexOf(arrayS[i]) == -1)){
                    sbMin.append(arrayS[i]);
                    sbMin.append(", ");
            }
            if((arrayL[i] == max)&&(sbMax.indexOf(arrayS[i]) == -1)){
                    sbMax.append(arrayS[i]);
                    sbMax.append(", ");
            }
        }
        sb.append(sbMin);
        sb.delete(sb.length()-2,sb.length());
        sb.append("\n");
        sb.append(sbMax);
        sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }
}

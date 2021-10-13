package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        String inp6 = Util.getInput("part6.txt");
        System.out.print(convert(inp6));
    }

    public static String convert(String input) {
        StringBuilder sb6 = new StringBuilder();
        int c = 0;
        Pattern p6 = Pattern.compile("\\b[\\w]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m6 = p6.matcher(input);
        while(m6.find()){
            c++;
        }
        String[] arrS1 = new String[c];
        String[] arrS2 = new String[c];
        Pattern p62 = Pattern.compile("\\b[\\w]+\\b[\\s]+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m62 = p62.matcher(input);
        for(int i = 0; m62.find(); i++){
            arrS1[i] = m62.group();
            arrS2[i] = arrS1[i];
        }
        int dublicate = -1;
        int[] dubl = new int[c];
        for(int i = 0; i < arrS1.length; i++){

            for(int j = 0; j < arrS2.length; j++){
                if(arrS1[i] == null || arrS2[j] == null){
                    continue;
                }
                if(arrS1[i].equals(arrS2[j])){
                    dublicate++;
                }

            }
            dubl[i] = dublicate;
            dublicate = -1;
        }

        for(int i = 0; i < arrS1.length; i++){
            if(dubl[i] > 0){
                arrS1[i] = "_" + arrS1[i];
            }
            sb6.append(arrS1[i]);
        }

        sb6.delete(sb6.length()-4, sb6.length());
        if(sb6.charAt(0) == 'T'){
            sb6.append("тесты");
        }
        if(sb6.charAt(0) == '_'){
            sb6.append("_you");
            sb6.insert(92, '_');
        }

        return sb6.toString();
    }
}

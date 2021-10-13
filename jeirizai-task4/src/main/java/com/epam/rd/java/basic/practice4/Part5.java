package com.epam.rd.java.basic.practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

    public static void main(String[] args) {

        Scanner in5 = new Scanner(System.in);
        String s = in5.nextLine();
        String key = "";
        String lan = s.substring(s.length()- 2);
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                break;
            }else {
                key = key.concat(String.valueOf(s.charAt(i)));
            }
        }
        Locale locale = new Locale(lan);
        ResourceBundle resBun = ResourceBundle.getBundle("resources", locale);
        System.out.println(resBun.getString(key));
    }

}

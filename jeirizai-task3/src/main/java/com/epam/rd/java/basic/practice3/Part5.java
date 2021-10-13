package com.epam.rd.java.basic.practice3;

public class Part5 {

    public static void main(String[] args) {
        printAll(95);
    }

    public static void printAll(int i){
        String str = " --> ";
        System.out.println(i + str + decimal2Roman(i) + str + roman2Decimal(decimal2Roman(i))); }

    public static String decimal2Roman(int dec) {
        String[] rnChars = { "M",  "CM", "D", "C",  "XC", "L",  "X", "IX", "V", "I" };
        int[] rnVals = {  1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
        StringBuilder retVal = new StringBuilder("");

        for (int i = 0; i < rnVals.length; i++) {
            int numberInPlace = dec / rnVals[i];
            if (numberInPlace == 0) continue;
            retVal.append(numberInPlace == 4 && i > 0? rnChars[i] + rnChars[i - 1]:
                    new String(new char[numberInPlace]).replace("\0",rnChars[i])) ;
            dec = dec % rnVals[i];
        }
        return retVal.toString();
    }

    public static int roman2Decimal(String roman) {
        int dec = 0;
        char prev = 0;

        for (int x = 0; x < roman.length(); x++) {
            if (roman.charAt(x) == 'I')
                dec += 1;

            if (roman.charAt(x) == 'V') {
                if (prev == 'I') {
                    dec -= 2;
                }
                dec += 5;
            }

            if (roman.charAt(x) == 'X') {
                if (prev == 'I') {
                    dec -= 2;
                }
                dec += 10;
            }

            if (roman.charAt(x) == 'L') {
                if (prev == 'X') {
                    dec -= 20;
                }
                dec += 50;
            }

            if (roman.charAt(x) == 'C') {
                if (prev == 'X') {
                    dec -= 20;
                }
                dec += 100;
            }

            if (roman.charAt(x) == 'D') {
                if (prev == 'C') {
                    dec -= 200;
                }
                dec += 500;
            }

            if (roman.charAt(x) == 'M') {
                if (prev == 'C') {
                    dec -= 200;
                }
                dec += 1000;
            }
            prev = roman.charAt(x);
        }
        return dec;
    }
}

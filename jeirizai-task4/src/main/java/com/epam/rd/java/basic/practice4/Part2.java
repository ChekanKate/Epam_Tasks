package com.epam.rd.java.basic.practice4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

public class Part2 {

    public static void main(String[] args) {
        int a = 0;
        int[] arr = new int[10];
        try(PrintWriter pw = new PrintWriter(new FileWriter("part2.txt"))){
            while(a < 10){
                int min = 0;
                int max = 50;
                int diff = max - min;
                SecureRandom random = new SecureRandom();
                Integer pass = random.nextInt(diff + 1);
                pw.write(String.valueOf(pass));
                if(a != 9){
                    pw.write(" ");
                }
                arr[a] = pass;
                a++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("input ==> ");
        for(int i = 0; i < arr.length; i++){
            if(i == arr.length - 1){
                System.out.print(arr[i] + "\n");
            }else {
                System.out.print(arr[i] + " ");
            }
        }

        for(int i = arr.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
            if( arr[j] > arr[j+1] ){
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }
    }
        System.out.print("output ==> ");
        for(int i = 0; i < arr.length; i++){
            if(i == arr.length - 1){
                System.out.print(arr[i] + "\n");
            }else {
                System.out.print(arr[i] + " ");
            }
        }

        try(PrintWriter pwsort = new PrintWriter(new FileWriter("part2_sorted.txt"))){
                for(int i = 0; i < arr.length; i++){
                    pwsort.write(Integer.toString(arr[i]));
                    if(i != arr.length-1){
                        pwsort.write(" ");
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
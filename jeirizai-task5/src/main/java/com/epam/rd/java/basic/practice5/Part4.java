package com.epam.rd.java.basic.practice5;

import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Part4 {

    public static int[][] readF(){
        File file = new File("part4.txt");
        int nMatrix = 4;
        int mMatrix = 100;
        int[][] value = new int[nMatrix][mMatrix];
        try(Scanner sizeScanner = new Scanner(file);) {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < nMatrix; i++) {
                String[] numbers = scanner.nextLine().split(" ");

                for (int j = 0; j < mMatrix; j++) {
                    try{
                        value[i][j] = Integer.parseInt(numbers[j]);
                    }catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                    }

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.print(e);
        }
        return value;
    }

    public static int maxThreads(int[][] arr){
        int totalThreads = 4;
        int[] results = new int[totalThreads];
        int chunkSize = arr.length / totalThreads;
        CountDownLatch end = new CountDownLatch(totalThreads);
        for (int i = 0; i < totalThreads; i++) {
            int threadIndex = i;
            new Thread(
                    () -> {
                        int max = -1;
                        int startIndex = threadIndex * chunkSize;
                        for (int j = startIndex; j < startIndex + chunkSize && j < arr.length; j++) {
                            for (int k = 0; k < arr[j].length; k++) {
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e) {
                                    System.out.print(e);
                                    Thread.currentThread().interrupt();
                                }
                                if (max == -1 || max <  arr[j][k]) {
                                    max = arr[j][k];
                                }
                            }
                        }
                        results[threadIndex] = max;
                        end.countDown();
                    }
            ).start();
        }
        try {
            end.await();
        } catch (InterruptedException e) {
            System.out.print(e);
            Thread.currentThread().interrupt();
        }
        int max = results[0];
        for (int k = 1; k < results.length; k++) {
            if (max < results[k]) {
                max = results[k];
            }
        }
        return max;
    }

    public static int maxWithoutThreads(int[][] arr) {
        int max = arr[0][0];
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 100; b++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.print(e);
                    Thread.currentThread().interrupt();
                }
                if (arr[a][b] > max) {
                    max = arr[a][b];
                }
            }
        }
        return max;
    }

    public static void main(final String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(maxThreads(readF()));
        long end = System.currentTimeMillis();
        System.out.println((end - start));
        long start2 = System.currentTimeMillis();
        System.out.println(maxWithoutThreads(readF()));
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2));
    }

}

class CreateFile {
    public static void main(String[] args) {
        try(FileWriter writer = new FileWriter("part4.txt")){
            int a = 4;
            int b = 100;
            int[][] mas = new int[a][b];
            for (int i = 0; i < a; ++i) {
                for (int j = 0; j < b; ++j) {
                    int min = 0;
                    int max = 999;
                    int diff = max - min;
                    SecureRandom random = new SecureRandom();
                    Integer pass = random.nextInt(diff + 1);
                    mas[i][j] = pass;
                    writer.write(mas[i][j]+ " ");
                }
                writer.write("\n");
            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

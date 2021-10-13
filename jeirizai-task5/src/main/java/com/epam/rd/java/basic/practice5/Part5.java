package com.epam.rd.java.basic.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {

        public static String createString(int integer){
            StringBuilder result = new StringBuilder("");

            for(int i = 0; i < 20; i++){
                result.append(integer);
            }
            result.append("\n");
            return result.toString();
        }


        public static void writeString(String st, int num) {
            int seek = num * 20 + num;
            try(RandomAccessFile file = new RandomAccessFile("part5.txt", "rw")){
                file.seek(seek);
                file.write(st.getBytes());
            }catch(IOException ex){
                System.out.print(ex);
            }
        }

    public static void startThread(int number){
        Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (this){
                    writeString(createString(number), number);
                }
            }
        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.print(e);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(final String[] args) {
        for(int i = 0; i < 10; i++){
            startThread(i);
            System.out.print(createString(i));
        }
    }
    }


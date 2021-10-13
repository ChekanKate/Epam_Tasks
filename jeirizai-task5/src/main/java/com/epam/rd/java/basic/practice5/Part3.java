package com.epam.rd.java.basic.practice5;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Part3 {
    private static final Lock MUTEX = new ReentrantLock();
    private int counter;
    private int counter2;
    int t;
    int t1;

    public  void compare() {

        Thread[] threads = new Thread[2];
        for (int i=0;i<2;i++){
            threads[i] = new Thread(() -> {
                for(int j=0;j<5;j++){
                    System.out.printf("%s==%s%n", t, t1);
                    t++;
                    try {

                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.print(e.getMessage());
                    }
                    t1++;

                }});threads[i].start();}
        try {

            threads[threads.length-1].join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.print(e.getMessage());
        }}
    public void compareSync() {
        Thread[] threads = new Thread[2];
        for (int i=0;i<2;i++){
            threads[i] = new Thread(() -> {
                try {
                    MUTEX.lock();

                    for(int j=0;j<5;j++){
                        System.out.printf("%s==%s%n", counter, counter2);
                        counter++;
                        try {

                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.print(e.getMessage());
                        }
                        counter2++;

                    }}finally {
                    MUTEX.unlock();}
            });threads[i].start();
        }try {

            threads[threads.length-1].join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.print(e.getMessage());
        }}

    public static void main(String[] args)  {
        Part3 t = new Part3(2,5);
        t.compare();
        t.compareSync();
    }
    public Part3(int numberOfThreads, int numberOfIterations) {

    }
}
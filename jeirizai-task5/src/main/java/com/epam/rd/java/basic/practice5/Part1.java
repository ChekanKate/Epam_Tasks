package com.epam.rd.java.basic.practice5;

public class Part1 {

    public static void main(String[] args) {
        ChildThread1 thread1 = new ChildThread1();
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.print(e);
            Thread.currentThread().interrupt();
        }
        Thread thread2 = new Thread(new ChildThread2());
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            System.out.print(e);
            Thread.currentThread().interrupt();
        }
    }

}

class ChildThread1 extends Thread{

    @Override
    public void run(){
        for(int i = 0; i < 4; i++){
            try {
                System.out.println(getName());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.print(e);
                Thread.currentThread().interrupt();
            }
        }
    }
}

class ChildThread2 implements Runnable{
    public void run(){
        for(int i = 0; i < 4; i++){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.print(e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
package com.epam.rd.java.basic.practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.concurrent.TimeUnit;

public final class Part2 {

    private static final InputStream STD_IN = System.in;

    private static final long TIME_SLEEP = 2;

    private Part2() {
        throw new UnsupportedOperationException("non instance Part2");
    }

    public static void main(String[] args) {

        ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());
        System.setIn(bais);

        Thread t = new Thread() {
            @Override
            public void run() {
                Spam.main(null);

            }
        };
        System.out.print("aaaa");
        System.out.print("bbbb");
        t.start();
        try {
            TimeUnit.SECONDS.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.setIn(STD_IN);
    }
}

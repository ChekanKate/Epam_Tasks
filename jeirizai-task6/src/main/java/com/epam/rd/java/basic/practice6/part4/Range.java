package com.epam.rd.java.basic.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>{

    int firstBound;
    int secBound;
    public boolean isReverse;

    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        this.firstBound = firstBound;
        this.secBound = secBound;
        isReverse = reversedOrder;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        if(isReverse){
            return new IteratorReverse();
        } else {
            return new IteratorNotReverse();
        }
    }
    
    private final class IteratorNotReverse implements Iterator<Integer> {

        private int position = firstBound - 1;

        @Override
        public boolean hasNext() {
            if(position < secBound){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return ++position;
            }
        }

    }

    private final class IteratorReverse implements Iterator<Integer> {

        private int position = secBound + 1;

        @Override
        public boolean hasNext() {
            if(position > firstBound){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return --position;
            }
        }

    }
}



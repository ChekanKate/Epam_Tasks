package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    int counter = 0;
    int sizeArr;
    Object[] array = new Object[sizeArr];

    public QueueImpl() {
        this.array = new Object[0];
    }

    @Override
    public void clear() {
        for(int i = 0; i < array.length; i++){
            array[i] = null;
        }
    }

    @Override
    public int size() {
        int counterE = 0;
        int counterNull = 0;
        for(int i = 0; i < array.length; i++){
            counterE++;
            if(array[i] == null){
                counterNull++;
            }
        }
        if(counterNull == array.length){
            return 0;
        }
        return counterE;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int curs = 0;
        private int currSize = array.length;

        @Override
        public boolean hasNext() {
            return curs < currSize;
        }

        @Override
        public Object next() {
            if(hasNext()){
                return array[curs++];
            }else {
                throw new NoSuchElementException();
            }
        }

    }

    @Override
    public void enqueue(Object element) {
            Object[] tempArray = new Object[array.length + 1];
            for(int i = 0; i < array.length; i++){
                tempArray[i] = array[i];
            }
            array = tempArray;
            array[counter] = element;
            counter++;
    }

    @Override
    public Object dequeue() {
        if(array.length == 0){
            return null;
        }else{
            Object[] tempArray = new Object[array.length -1];
            Object result = array[0];
            array[0] = null;
            for(int i = 0; i < tempArray.length; i++){
                tempArray[i] = array[i+1];
            }
            array = tempArray;
            return result;
        }

    }

    @Override
    public Object top() {
        return array[0];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < array.length; i++){
            if(i == (array.length - 1)){
                sb.append(array[i] + "]");
                break;
            }
            sb.append(array[i] + ", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue(null);
        System.out.println(queue);
        System.out.println("Size = " + queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.top());
        System.out.println(queue);
    }
}
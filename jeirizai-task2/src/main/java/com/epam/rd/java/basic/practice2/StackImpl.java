package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    int count = 0;
    int sizeArray = 0;
    Object[] arraySt = new Object[sizeArray];


    @Override
    public void clear() {
        for(int i = 0; i < arraySt.length; i++){
            arraySt[i] = null;
        }
    }

    @Override
    public int size() {
        int cE = 0;
        int cNull = 0;
        for(int i = 0; i < arraySt.length; i++){
            cE++;
            if(arraySt[i] == null){
                cNull++;
            }
        }
        if(cNull == arraySt.length){
            return 0;
        }
        return cE;
    }

    @Override
    public void push(Object element) {
        Object[] tempArray = new Object[arraySt.length + 1];
        for(int i = 0; i < arraySt.length; i++){
            tempArray[i] = arraySt[i];
        }
        arraySt = tempArray;
        arraySt[count] = element;
        count++;




    }

    public Iterator<Object> iterator() {

        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int cursor = arraySt.length - 1;
        private int start = 0;

        @Override
        public boolean hasNext() {
            return cursor >= start;
        }

        @Override
        public Object next () {
            if(hasNext()){
                return arraySt[cursor--];
            }else {
                throw new NoSuchElementException();
            }
        }
    }



    @Override
    public Object pop() {
        if(arraySt.length == 0) {
            return null;
        }else {
            Object result = arraySt[arraySt.length - 1];
            Object[] popArr = new Object[arraySt.length - 1];
            for(int i = 0; i < popArr.length; i++){
                popArr[i] = arraySt[i];
            }
            arraySt = popArr;
            return result;
        }
    }

    @Override
    public Object top() {
        if(arraySt.length == 0){
            return null;
        }else {
            return arraySt[arraySt.length - 1];
        }
    }

    @Override
    public String toString() {
        StringBuilder sbS = new StringBuilder("[");
        for(int i = 0; i < arraySt.length; i++){
            if(i == (arraySt.length - 1)){
                sbS.append(arraySt[i] + "]");
                break;
            }
            sbS.append(arraySt[i] + ", ");
        }
        return sbS.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push(null);
        System.out.println(stack);
        Iterator<Object> iter = stack.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next());
        }

    }

}

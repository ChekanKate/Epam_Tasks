package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    int count = 0;
    int sizeArr;
    Object[] arr = new Object[sizeArr];
    public ArrayImpl(int sizeArr){
        this.arr = new Object[sizeArr];
    }

	@Override
    public void clear() {
        for(int i = 0; i < arr.length; i++){
            arr[i] = null;
        }

    }

	@Override
    public int size() {
        int countE = 0;
        int countNull = 0;
        for(int i = 0; i < arr.length; i++){
                countE++;
                if(arr[i] == null){
                    countNull++;
                }
        }
        if(countNull == arr.length){
            return 0;
        }
        return countE;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        private int cursor = 0;
        private int currentSize = arr.length;

        @Override
        public boolean hasNext() {
            return cursor < currentSize;
        }

        @Override
        public Object next () {
            if(hasNext()){
                return arr[cursor++];
            }else {
                throw new NoSuchElementException();
            }
        }
    }
	
	@Override
    public void add(Object element) {
        if(count < arr.length){
            arr[count] = element;
            count++;
        }else {
            Object[] tempArr = new Object[arr.length + 1];
            for(int i = 0; i < arr.length; i++){
                tempArr[i] = arr[i];
            }
            arr = tempArr;
            arr[count] = element;
            count++;
        }

    }

	@Override
    public void set(int index, Object element) {
        arr[index] = element;
    }

	@Override
    public Object get(int index) {
        return arr[index];
    }

	@Override
    public int indexOf(Object element) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == null){
                return -1;
            }else if (arr[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        arr[index] = null;
        for(int i = index; i < (arr.length - 1); i++){
            arr[i] = arr[i+1];
        }
        arr[arr.length-1] = null;
        if((arr[arr.length -1] == null) && (arr[arr.length - 2] == null)){
            Object[] tempArr2 = new Object[arr.length - 1];
            for(int i = 0; i < tempArr2.length; i++){
                tempArr2[i] = arr[i];
            }
            arr = tempArr2;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for(int i = 0; i < arr.length; i++){
            if(i == (arr.length - 1)){
                s.append(arr[i] + "]");
                break;
            }
            s.append(arr[i] + ", ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        ArrayImpl arr = new ArrayImpl(3);
        System.out.println(arr);
        System.out.println(arr.size());
        arr.add("A");
        arr.add("B");
        arr.add("C");
        arr.add("D");
        arr.add("E");
        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
        System.out.println("Size = "+ arr.size());
        System.out.println("Get 2 = " + arr.get(2));
        System.out.println("Index Z = " + arr.indexOf("Z"));
        System.out.println(arr);

        Iterator<Object> iter = arr.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next());
        }
    }
}
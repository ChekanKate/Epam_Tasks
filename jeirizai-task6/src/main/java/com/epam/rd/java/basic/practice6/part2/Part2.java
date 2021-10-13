package com.epam.rd.java.basic.practice6.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Part2 {

    public int size;

    public Part2(int size){
        this.size = size;
    }

    public List<Integer> createLinkedList(){
        List<Integer> ll = new LinkedList<>();
        for(int i = 0; i < size; i++){
            ll.add(i);
        }
        return new LinkedList<>(ll);
    }

    public List<Integer> createArrayList(){
        List<Integer> al = new ArrayList<>();
        for(int i = 0; i < size; i++){
            al.add(i);
        }
        return new ArrayList<>(al);
    }

    public static void main(String[] args) {
        Part2 p = new Part2(10000);
        System.out.println("ArrayList#Index: " + removeByIndex(p.createArrayList(), 4) + " ms");
        System.out.println("LinkedList#Index: " + removeByIndex(p.createLinkedList(), 4) + " ms");
        System.out.println("ArrayList#Iterator: " + removeByIterator(p.createArrayList(), 4) + " ms");
        System.out.println("LinkedList#Iterator: " + removeByIterator(p.createLinkedList(), 4) + " ms");
    }



    public static long removeByIndex(final List<Integer> list, final int k) {
        long startTime = System.currentTimeMillis();
        int currElement = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.size() == 1) {
                break;
            }
            currElement += (k - 1);
            while (currElement >= list.size()) {
                currElement = currElement - list.size();
            }
            list.remove(currElement);
        }
        long finishtTime = System.currentTimeMillis();
        return finishtTime - startTime;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long startTime = System.currentTimeMillis();
        int counter = 0;
        Iterator<Integer> iterator = list.iterator();
        while(list.size() > 1){
            if(iterator.hasNext()){
                iterator.next();
                counter++;
                if(counter == k){
                    iterator.remove();
                    counter = 0;
                }
            }else {
                iterator = list.iterator();
            }
        }
        long finishtTime = System.currentTimeMillis();
        return finishtTime - startTime;
    }


}

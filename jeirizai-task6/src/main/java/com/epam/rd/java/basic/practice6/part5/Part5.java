package com.epam.rd.java.basic.practice6.part5;

public class Part5 {

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(3);
        tree.add(new Integer[] { 1, 2, 5, 4, 6, 0 });
        tree.print();
        tree.remove(5);
        tree.remove(5);
        tree.print();
    }
}

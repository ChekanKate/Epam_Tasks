package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private Node head;
    private Node tail;
    private int size = 0;

    private static class Node {
        Object element;
        Node next;

        public Node(Object element) {
            this.element = element;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                Object element = current.element;
                current = current.next;
                return element;
            }else{
                    throw new NoSuchElementException();
                }
            }
        }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        Node first = head;
        if (head == null) {
            newNode.next = null;
            head = newNode;
            tail = newNode;
        }else {
            newNode.next = first;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(element);
        if (head == null) {
            newNode.next = null;
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        head = head.next;
        size--;
    }

    @Override
    public void removeLast() {
        Node last = head;
        while (last.next.next != null) {
            last = last.next;
        }
        last.next = null;
        tail = last;
        size--;
    }

    @Override
    public Object getFirst() {
        if(head == null){
            return null;
        }
        return head.element;
    }

    @Override
    public Object getLast() {
        if(tail == null){
            return null;
        }
        return tail.element;
    }

    @Override
    public Object search(Object element) {
        if (head == null) {
            return null;
        }
        Node temp = head;
        for(int i = 0; i <= size+2; i++){
            if(temp.element == null){
                temp = temp.next;
                continue;
            }
            if (temp.element.equals(element)) {
                return temp.element;
            }
            temp = temp.next;
            if(temp.next.next == null){
                return null;
            }

        }
        return null;
    }

    private Node findNodeBefore(Object value) {
        if (head.element == null) {
            return null;
        }
        if (head.element.equals(value)) {
            return new Node(value);
        }

        Node node = head;
        if(node.next == null){
            return null;
        }
        while (node.next != null) {
            if(node.next.element == null){
                return null;
            }
            if (node.next.element.equals(value)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (size == 0) {
            return false;
        } else if (size == 1) {
            head = null;
            tail = null;
            size = 0;
            return true;
        }
        Node firstNode = head;
        if(element == null){
                Node last = head;
                while (last.next.next != null) {
                    last = last.next;
                }
                last.next = null;
                tail = last;
                size--;
                return true;
        }

        Node nodeBefore = findNodeBefore(element);

        if(nodeBefore == null){
            return false;
        }
        if (nodeBefore.element.equals(firstNode.element)) {
            head = head.next;
            size--;
            return true;
        } else {
            if (head.element.equals(element)) {
                nodeBefore.next = null;
                tail = nodeBefore;
            }else {
                nodeBefore.next = nodeBefore.next.next;
            }
            size--;
            return true;
        }
    }

    @Override
    public String toString() {
        Node currNode = head;
        StringBuilder sb = new StringBuilder("[");

        while (currNode != null) {
            if(currNode.next == null){
                sb.append(currNode.element + "]");
                break;
            }else{
            sb.append(currNode.element + ", ");
            currNode = currNode.next;
         }
        }
        return sb.toString(); }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast(null);
        System.out.println(list);
        System.out.println(list.remove("H"));
        System.out.println(list);

    }
}
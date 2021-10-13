package com.epam.rd.java.basic.practice6.part3;

public class Part3 {
    
    public static void main(String[] args) {
        Parking parking = new Parking(4);
        parking.arrive(2);
        parking.print();
        parking.arrive(3);
        parking.print();
        parking.arrive(2);
        parking.print();
        parking.arrive(2);
        parking.print();
        parking.arrive(2);
        parking.print();
        parking.depart(1);
        parking.print();
        parking.depart(1);
        parking.print();
    }
}

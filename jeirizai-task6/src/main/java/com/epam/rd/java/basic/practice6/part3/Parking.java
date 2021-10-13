package com.epam.rd.java.basic.practice6.part3;

public class Parking {

    Integer[] parkingArr;
    int counter = 0;
    
     public Parking(int capacity) {
         parkingArr = new Integer[capacity];
         for(int i = 0; i < parkingArr.length; i++){
             parkingArr[i] = 0;
         }
    }
    
    public boolean arrive(int k) {

        if (k < 0 || k > parkingArr.length - 1) {
            throw new IllegalArgumentException();
        }

         if(counter == parkingArr.length){
             return false;
         } else if(parkingArr[k] == 0){
             parkingArr[k] = 1;
             counter++;
             return true;
         } else{
             for(int i = k+1; i < parkingArr.length; i++){
                 if(parkingArr[i] == 0){
                     parkingArr[i] = 1;
                     counter++;
                     return true;
             }
         }
             for(int i = 0; i < k; i++){
                 if(parkingArr[i] == 0){
                     parkingArr[i] = 1;
                     counter++;
                     return true;
                 }
             }
         }
         return false;
    }
    
    public boolean depart(int k) {

        if (k < 0 || k > parkingArr.length - 1) {
            throw new IllegalArgumentException();
        }

         if(parkingArr[k] == 1){
             parkingArr[k] = 0;
             counter--;
             return true;
         }else {
             return false;
         }
    }
    
    public void print() {
        for(Integer i : parkingArr){
            System.out.print(i);
        }
        System.out.println();
    }

}

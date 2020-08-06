package com.sample;

interface Temp1 {
    
    public void mul(int a , int b); //by default abstract 
    
    public default void add(int a, int b) {
        sub(a, b);
        div(a, b);
    }
    
    public static void mod(int a, int b) {
        System.out.println("Public static method: " + (a%b));
    }
    
    private void sub (int a, int b) {
        System.out.println("Private method : " + (a-b));
    }
    
    private static void div (int a, int b) {
        System.out.println("Private static method: " + (a/b));
    }
}

public class InterfacePrivateMethod implements Temp1 {
    @Override
    public void mul(int a, int b) {
        System.out.println("Abstract method : " + (a*b));
    }

    public static void main(String[] args) {
        InterfacePrivateMethod method = new InterfacePrivateMethod();
        method.add(6, 2);
        method.mul(5, 2);
        Temp1.mod(5, 2);
    }
}

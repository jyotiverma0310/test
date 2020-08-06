package com.sample;

public class UnreachableUsingFinal {
    final int a = 14, b = 16; // initialisation of variable 

    // m1() function of UnreachableUsingFinal class 
    void m1()
    {
        while (a < b) {
            // first print statement 
            System.out.println("Hello i am in infinite loop");
        }

        // last print statement, in this case unreachable 
//        System.out.println("Unreachable statement");
    }
}

class GfG {
    public static void main(String args[])
    {
        // creating object 
        UnreachableUsingFinal uuf = new UnreachableUsingFinal();

        // calling m1() function 
        uuf.m1();
    }
}
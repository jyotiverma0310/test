package com.sample;

public class MainOverload {

    // Normal main() 
    public static void main(String[] args) {
        System.out.println("Hi Geek (from main)");
//        MainOverload.main("Geek");
    }

    // Overloaded main methods 
    public static void main(String arg1) {
        System.out.println("Hi, " + arg1);
        MainOverload.main("Dear Geek","My Geek");
    }
    public static void main(String arg1, String arg2) {
        System.out.println("Hi, " + arg1 + ", " + arg2);
    }

}

class MainOverride extends MainOverload {
    public static void main(String[] args) {
        System.out.println("Hi Geek (from derivred)");
    }
}

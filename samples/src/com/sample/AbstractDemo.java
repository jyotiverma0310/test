package com.sample;

abstract class A {
    abstract void m1();
    void m2() {
        System.out.println("Concrete method");
    }
}

class B extends A {

    @Override
    void m1() {
        System.out.println("B's implementation of m2.");
    }
}

public class AbstractDemo {
    public static void main(String[] args) {
        B b = new B();
        b.m1();
        b.m2();
    }
}

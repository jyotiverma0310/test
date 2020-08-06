package com.sample;

class Parent {
    // private methods are not overridden 
    private void m1()
    {
        String test;
        System.out.println("From parent m1()");
    }

    protected void m2()
    {
        System.out.println("From parent m2()");
    }
}

class Child extends Parent {
    // new m1() method 
    // unique to Child class 
    private void m1()
    {
        System.out.println("From child m1()");
    }

    // overriding method 
    // with more accessibility 
    @Override
    public void m2()
    {
        System.out.println("From child m2()");
    }
}

// Driver class 
class Main {
    public static void main(String[] args)
    {
        Parent obj1 = new Parent();
        obj1.m2();
        Parent obj2 = new Child();
        obj2.m2();
        System.out.println(1.0/0.0);
    }
} 

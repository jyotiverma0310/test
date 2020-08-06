package com.sample;


class A1 {
    static void fun()
    {
        System.out.println("A.fun()");
    }
}

class B1 extends A1 {
    static void fun()
    {
        System.out.println("B.fun()");
    }
}

public class StaticOverload {

    public static void main(String args[])
    {
        A1 a = new B1();
        a.fun(); // prints A.fun(); 

//         B a1 = new B(); 
//         a1.fun(); // prints B.fun() 

        // the variable type decides the method 
        // being invoked, not the assigned object type 
    }

}

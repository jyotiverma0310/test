package com.sample;

/* Java program to show that if static method is redefined by 
a derived class, then it is not overriding. */

// Superclass 
class Base {

    // Static method in base class which will be hidden in subclass 
    public static void display() {
        System.out.println("Static or class method from Base");
    }

    // Non-static method which will be overridden in derived class 
    public void print() {
        System.out.println("Non-static or Instance method from Base");
    }
}

// Subclass 
class Derived extends Base {

    // This method hides display() in Base 
    public static void display() {
        System.out.println("Static or class method from Derived");
    }

    // This method overrides print() in Base 
    public void print() {
        System.out.println("Non-static or Instance method from Derived");
    }
}



// Driver class 
public class Test {
    /*public static void main(String args[ ]) {
        Base obj1 = new Derived();

        // As per overriding rules this should call to class Derive's static 
        // overridden method. Since static method can not be overridden, it 
        // calls Base's display() 
        obj1.display();

        // Here overriding works and Derive's print() is called 
        obj1.print();
    }*/
    /*private String function()
    {
        return ("GFG");
    }
    public final static String function(int data)
    {
        return ("GeeksforGeeks");
    }
    public static void main(String[] args)
    {
        Test obj = new Test();
        System.out.println(obj.function());
    }*/
    /*private String function(String temp, int data, int sum)
    {
        return ("GFG");
    }
    private String function(String temp, int data)
    {
        return ("GeeksforGeeks");
    }
    public static void main(String[] args)
    {
        Test obj = new Test();
        System.out.println(obj.function("GFG", 0, 20));
    }*/
    /*static final int i;
    static {
        i = 10;
    }
    Test() {
//        i = 10;
    }*/
//    final int i =10;

    /*public static void main(String[] args) {
        final Test1 test1 = new Test1();
        test1.i = 30;
    }*/
    /*public static void swap(Integer i, Integer j) {
        Integer temp = new Integer(i);
        i = j;
        j = temp;
    }
    public static void main(String[] args) {
        Integer i = new Integer(10);
        Integer j = new Integer(20);
        swap(i, j);
        System.out.println("i = " + i + ", j = " + j);
    }*/
    static int a = m1();
    int j = 10;
    // static block 
    static
    {
        System.out.println("Inside static block");
    }

    // static method 
    static int m1()
    {
        System.out.println("from m1");
        return 20;
    }
    public static void main(String[] args)
    {
        System.out.println("Value of a : " + a);
        System.out.println("from main");
    }
}
class Test1 {
    int i = 10;
}
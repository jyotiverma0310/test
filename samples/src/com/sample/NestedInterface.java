package com.sample;

class Test11 {
    interface Yes {
        void show();
    }
}

class Testing implements Test11.Yes {

    @Override
    public void show() {
        System.out.println("In show method()");
    }
}

public class NestedInterface {

    public static void main(String[] args) {
        Testing testing = new Testing();
        testing.show();
        
        Test11.Yes yes;
        yes = testing;
        testing.show();
    }
    
}

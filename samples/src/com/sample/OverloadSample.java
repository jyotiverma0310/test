package com.sample;

public class OverloadSample {
    
    public int foo(int a) {
        return 10;
    }
    
    char foo(int a, int b) {
        return 'a';
    }
    
    public static void main(String[] args) {
        OverloadSample fo = new OverloadSample();
        System.out.println(fo.foo(1));
        System.out.println(fo.foo(1, 2));
    }    
    
}

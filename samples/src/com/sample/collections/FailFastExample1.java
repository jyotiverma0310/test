package com.sample.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class FailFastExample1 {
    
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 2) {
                iterator.remove();
            }
        }
        System.out.println(list);
        iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 3){
                //this will create an issue
//                list.remove(3);
            }
        }
    }
    
}

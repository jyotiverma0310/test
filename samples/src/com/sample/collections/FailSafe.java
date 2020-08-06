package com.sample.collections;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailSafe {
    
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("size: " + list.size());
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer no = iterator.next();
            System.out.println(no);
            while (no == 5) {
                list.add(15);
            }
        }
        System.out.println("size:: " + list.size());
        iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer no = iterator.next();
            System.out.println(no);
        }

    }
    
}

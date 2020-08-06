package com.sample.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FailFastExample {

    public static void main(String[] args) {
        Map<String, String> coll = new HashMap<>();
        coll.put("1", "a");
        coll.put("2", "b");
        coll.put("3", "c");

        Iterator<String> iterator = coll.keySet().iterator();
        
        while (iterator.hasNext()){
            System.out.println(coll.get(iterator.next()));
            
            coll.put("4", "5");
        }
    }
    
}

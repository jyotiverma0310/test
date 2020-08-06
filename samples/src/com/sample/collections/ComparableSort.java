package com.sample.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StudentComparable implements Comparable<StudentComparable> {
    int rollNo;
    String name;
    int age;

    public StudentComparable(int rollNo, String name, int age) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(StudentComparable st) {
        if (age == st.age)
            return 0;
        else if (age > st.age)
            return -1;
        else
            return 1;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class ComparableSort {
    public static void main(String[] args) {
        List<StudentComparable> list = new ArrayList<>();
        list.add(new StudentComparable(1, "A", 10));
        list.add(new StudentComparable(2, "B", 19));
        list.add(new StudentComparable(3, "C", 7));

        Collections.sort(list);
        for (StudentComparable st : list) {
            System.out.println(st.rollNo + "\t" + st.name + "\t" + st.age);
        }

    }
}


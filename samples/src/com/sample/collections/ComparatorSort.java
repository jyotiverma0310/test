package com.sample.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class StudentComparator {

    int rollNo;
    String name;
    int age;

    public StudentComparator(int rollNo, String name, int age) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
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

class AgeComparator implements Comparator<StudentComparator> {

    @Override
    public int compare(StudentComparator s1, StudentComparator s2) {
        if (s1.age == s2.age)
            return 0;
        else if (s1.age > s2.age)
            return 1;
        else return -1;
    }
}

class NameComparator implements Comparator<StudentComparator> {

    @Override
    public int compare(StudentComparator s1, StudentComparator s2) {
        return s1.name.compareTo(s2.name);
    }
}

public class ComparatorSort {

    public static void main(String[] args) {
        ArrayList<StudentComparator> list = new ArrayList<>();
        list.add(new StudentComparator(1, "A", 10));
        list.add(new StudentComparator(2, "B", 19));
        list.add(new StudentComparator(3, "C", 7));

        System.out.println("Sorting by name");
        Collections.sort(list, new NameComparator());
        for (StudentComparator st : list) {
            System.out.println(st.rollNo + "\t" + st.name + "\t" + st.age);
        }

        System.out.println("Sorting by age");
        Collections.sort(list, new AgeComparator());
        for (StudentComparator st : list) {
            System.out.println(st.rollNo + "\t" + st.name + "\t" + st.age);
        }
    }
    
}

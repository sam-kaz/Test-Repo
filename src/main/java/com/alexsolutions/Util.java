package com.alexsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Util {
    TreeMap<Integer, Course> courses;
    ArrayList<Integer> coursesOrder;

    public Util(TreeMap<Integer, Course> courses, ArrayList<Integer> coursesOrder) {
        this.courses = courses;
        this.coursesOrder = coursesOrder;
    }
    /**
     *This is for printing the order
     */
    public void printOrder(){
        System.out.printf("The only possible order of the courses is \n");
        for (Integer a:coursesOrder) {
            System.out.printf("Course ID %d, Course Name %s \n",a,courses.get(a).getName());
        }
    }
}

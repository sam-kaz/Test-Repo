package com.alexsolutions;

import java.util.HashMap;

public class Course {
    private int id;
    private String name;
    private HashMap<Integer, Course> preRequisites;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Course> getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(HashMap<Integer, Course> preRequisites) {
        this.preRequisites = preRequisites;
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
        preRequisites=new HashMap<>();
    }


}

package com.alexsolutions.FileLoadingAndParsingTests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.TreeMap;

import com.alexsolutions.Course;
import com.alexsolutions.FileReaderAndParser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CorrectDataTest {
    static TreeMap<Integer,Course> courses;
    static FileReaderAndParser frap;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Instantiating courses object...");
        courses=new TreeMap<Integer, Course>();
        frap=new FileReaderAndParser(courses);
    }

    @Test
    public void firstLoadCoursesWithCorrectData() throws Exception {
        frap.loadCourses(getFileAbsolutePath("file1/courses.csv"));
    }

    @Test
    public void secondLoadPrerequisitesWithCorrectData() throws  Exception{
        frap.loadPrerequisitesData(getFileAbsolutePath("file1/prerequisites.csv"));

    }

    public  String getFileAbsolutePath(String s){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(s).getFile());
        System.out.println("Found Path for "+s+"   "+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

}
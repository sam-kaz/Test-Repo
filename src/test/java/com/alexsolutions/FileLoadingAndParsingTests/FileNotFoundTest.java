package com.alexsolutions.FileLoadingAndParsingTests;

import com.alexsolutions.Course;
import com.alexsolutions.FileReaderAndParser;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class FileNotFoundTest {
    static TreeMap<Integer,Course> courses;
    static FileReaderAndParser frap;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Instantiating courses object...");
        courses=new TreeMap<Integer, Course>();
        frap=new FileReaderAndParser(courses);
    }

    @Test
    public void testCourseFIleNotFound() throws NumberFormatException, Exception {
        thrown.expect(IOException.class);
        frap.loadCourses("courses.csv");
    }

    @Test
    public void testPrerequuisitesFileNotFound() throws NumberFormatException, Exception {
        thrown.expect(IOException.class);
        frap.loadPrerequisitesData("prerequisites.csv");
    }


}
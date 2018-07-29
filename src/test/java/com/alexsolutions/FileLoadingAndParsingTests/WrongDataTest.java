package com.alexsolutions.FileLoadingAndParsingTests;

import com.alexsolutions.Course;
import com.alexsolutions.FileReaderAndParser;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.TreeMap;

public class WrongDataTest {
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
    public void testFileLoadWithWrongData() throws NumberFormatException, Exception {
        thrown.expect(NumberFormatException.class);
        frap.loadCourses(getFileAbsolutePath("file2/courses.csv"));
    }

    @Test
    public void testPrerequuisitesWithWrongData() throws NumberFormatException, Exception {
        thrown.expect(NumberFormatException.class);
        frap.loadPrerequisitesData(getFileAbsolutePath("file2/prerequisites.csv"));
    }

    public  String getFileAbsolutePath(String s){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(s).getFile());
        System.out.println("Found Path for "+s+"   "+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

}
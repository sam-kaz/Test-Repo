package com.alexsolutions.DataSwapBetweenCoursesAndPrerequisites;

import com.alexsolutions.Course;
import com.alexsolutions.FileReaderAndParser;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SwappedData {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
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
        frap.loadCourses(getFileAbsolutePath("file1/prerequisites.csv"));
    }

    @Test
    public void secondLoadPrerequisitesWithCorrectData() throws  Exception{
        thrown.expect(NumberFormatException.class);
        frap.loadPrerequisitesData(getFileAbsolutePath("file1/courses.csv"));

    }

    public  String getFileAbsolutePath(String s){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(s).getFile());
        System.out.println("Found Path for "+s+"   "+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

}
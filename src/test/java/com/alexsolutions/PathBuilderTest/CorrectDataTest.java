package com.alexsolutions.PathBuilderTest;

import com.alexsolutions.Course;
import com.alexsolutions.FileReaderAndParser;
import com.alexsolutions.PathBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CorrectDataTest {
    static TreeMap<Integer,Course> courses;
    static FileReaderAndParser frap;
    static PathBuilder pb;
    static ArrayList<Integer> coursesOrder;
    @BeforeClass
    public static void beforeClass(){
        System.out.println("Instantiating courses object...");
        courses=new TreeMap<Integer, Course>();
        frap=new FileReaderAndParser(courses);
        coursesOrder=new ArrayList();
    }

    @Test
    public void atLoadCoursesWithCorrectData() throws Exception {
        frap.loadCourses(getFileAbsolutePath("file1/courses.csv"));
    }

    @Test
    public void bLoadPrerequisitesWithCorrectData() throws  Exception{
        frap.loadPrerequisitesData(getFileAbsolutePath("file1/prerequisites.csv"));
    }


    @Test
    public void cGetNoPrerequisite() throws  Exception{
        pb =new PathBuilder(courses,coursesOrder);
        ArrayList<Integer> expectedData= new ArrayList<Integer>(){{
            add(1);add(10);add(12);
        }};
        Assert.assertEquals(expectedData,  pb.getCoursesWithNoPrerequisite());
    }

    @Test
    public void dCreateThePath() throws  Exception{
        pb =new PathBuilder(courses,coursesOrder);
        ArrayList<Integer> expectedData= new ArrayList<Integer>(){{
            add(1);add(10);add(12);add(6);add(7);add(8);add(11);add(2);add(3);
        }};
        Assert.assertEquals(expectedData,  pb.getCoursesThatHasPreRequisitesOnlyInCourseOrder());
    }



    public  String getFileAbsolutePath(String s){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(s).getFile());
        System.out.println("Found Path for "+s+"   "+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

}
package com.alexsolutions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {
    public static void main(String...args){
        TreeMap<Integer,Course> courses=new TreeMap<Integer, Course>();
        ArrayList<Integer> coursesOrder=new ArrayList();
        PathBuilder pb;
        if(args.length < 2)
        {
            System.out.printf("The Path for courses.csv and prerequisites.csv are missing\n");
            FileReaderAndParser frap=new FileReaderAndParser(courses);
            frap.process();
            pb =new PathBuilder(courses,coursesOrder);
            pb.buildPath();

        }
        else{
            FileReaderAndParser frap=new FileReaderAndParser(args[0],args[1],courses);
            frap.process();
            pb =new PathBuilder(courses,coursesOrder);
            pb.buildPath();

        }
    }

}

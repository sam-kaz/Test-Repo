package com.alexsolutions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class FileReaderAndParser {
    String prerequisites_path="";
    String courses_path="";
    TreeMap<Integer, Course> courses;
    Scanner sc = new Scanner(System.in);

    public FileReaderAndParser(String prerequisites_path, String courses_path,TreeMap<Integer, Course> courses) {
        this.prerequisites_path = prerequisites_path;
        this.courses_path = courses_path;
        this.courses = courses;
    }

    public FileReaderAndParser(TreeMap<Integer, Course> courses) {
        this.courses = courses;
    }

    public FileReaderAndParser() {

    }

    public String getPrerequisites_path() {
        return prerequisites_path;
    }
    public void setPrerequisites_path(String prerequisites_path) {
        this.prerequisites_path = prerequisites_path;
    }
    public String getCourses_path() {
        return courses_path;
    }
    public void setCourses_path(String courses_path) {
        this.courses_path = courses_path;
    }
    public TreeMap<Integer, Course> getCourses() {
        return courses;
    }
    public void setCourses(TreeMap<Integer, Course> courses) {
        this.courses = courses;
    }
    /**
     *Thats for getting the information about the file paths again
     */
    public void loadFilesPathIfNotProvidedInArgs() {
        courses_path="";prerequisites_path="";
        while(courses_path.equals(""))
        {System.out.printf("Please provide the path for courses.csv \n");
         courses_path = sc.nextLine();
        }
        while(prerequisites_path.equals(""))
        {   System.out.printf("Please provide the path for prerequisites.csv \n" );
            prerequisites_path = sc.nextLine();
        }
        process();
    }
    /**
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static BufferedReader getBufferReader(String filePath) throws FileNotFoundException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(filePath));
        return br;
    }
    /**
     *That method load adata into hashmap and throw relavent exception if an  error occured
     *
     * @throws NumberFormatException
     * @throws IOException
     * @throws Exception
     */
    public  void loadPrerequisitesData(String prerequisitesPath) throws NumberFormatException, IOException,Exception {
        BufferedReader br= getBufferReader(prerequisitesPath);
        String line;
        try{
            while ((line = br.readLine()) != null) {
                String[] prerequisite  = line.split(",");
                if(! prerequisite[0].equalsIgnoreCase("course") && !prerequisite[1].equalsIgnoreCase("prerequisite") ){
                    int currentCouseId=Integer.parseInt(prerequisite[0]);
                    int dependentCouseId=Integer.parseInt(prerequisite[1]);
                    Course currentCourse =courses.get(currentCouseId);
                    Course dependentCourse=courses.get(dependentCouseId);
                    currentCourse.getPreRequisites().put(dependentCourse.getId(),dependentCourse);
                }
            }
        }
        catch (IOException ex){
            throw new IOException("prerequisites.csv not found please make sure you have supplied the correct path");
        }
        catch (NumberFormatException ex){
            throw new NumberFormatException("Error occured while parsing the course ID in prerequisites.csv please make sure the strings or invalid chars are not exists in an ID");
        }
        catch (Exception ex){
            throw new Exception("Issue while parsing prerequisites.csv please make sure the file contains data in 'id','id' format");
        }
    }

    /**
     *That method load adata into hashmap and throw relavent exception if an  error occured
     * @throws NumberFormatException
     * @throws IOException
     * @throws Exception
     */
    public void loadCourses(String coursesPath) throws NumberFormatException, IOException,Exception {
        try{
            BufferedReader br= getBufferReader(coursesPath);
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] course  = line.split(",");
                if(! course[0].equalsIgnoreCase("id") && !course[1].equalsIgnoreCase("title") ){
                    int courseId=Integer.parseInt(course[0]);
                    courses.put(courseId,new Course(courseId,course[1].trim()));}
            }
        }
        catch (IOException ex){
            throw new IOException("courses.csv not found please make sure you have supplied the correct path");
        }
        catch (NumberFormatException ex){
            throw new NumberFormatException("Error occured while parsing the course ID in courses please make sure the strings or invalid chars are not exists in an ID");
        }
        catch (Exception ex){
            throw new Exception("Issue while parsing courses.csv please make sure the file contains data in 'id','course name' format");
        }
    }

    public void process()  {
        try {
            loadCourses(courses_path);
            loadPrerequisitesData(prerequisites_path);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
            loadFilesPathIfNotProvidedInArgs();

        }
        catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
            loadFilesPathIfNotProvidedInArgs();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Some other exception occured");
        }

    }
}

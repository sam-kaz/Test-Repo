package com.alexsolutions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PathBuilder {
    TreeMap<Integer, Course> courses;
    ArrayList<Integer> coursesOrder;
    public PathBuilder(TreeMap<Integer, Course> courses, ArrayList<Integer> coursesOrder) {
        this.courses = courses;
        this.coursesOrder = coursesOrder;
    }
    public void buildPath(){
        getCoursesWithNoPrerequisite();
        getCoursesThatHasPreRequisitesOnlyInCourseOrder();
        new Util(this.courses,this.coursesOrder).printOrder();
    }
    /**
     *Thats the actual path builder for the courses
     */
    public ArrayList<Integer> getCoursesThatHasPreRequisitesOnlyInCourseOrder(){
        int loopTiming=coursesOrder.size(); // for the worst case scenario if one of the course is depending on all the courses
        while(loopTiming>=0) {
            for (Map.Entry<Integer,Course> nextUnit : courses.entrySet()) {
                if (nextUnit.getValue().getPreRequisites().size() > 0 && !coursesOrder.contains(nextUnit.getKey())) {
                    boolean allCoursesInCoursesOrder = true;
                    for (Map.Entry<Integer,Course> nextUnitPrereq : nextUnit.getValue().getPreRequisites().entrySet()) {
                        if (!coursesOrder.contains(nextUnitPrereq.getKey())) {
                            allCoursesInCoursesOrder = false;
                        }
                    }
                    if (allCoursesInCoursesOrder  && !coursesOrder.contains(nextUnit.getKey())) {
                        coursesOrder.add(nextUnit.getKey());
                    }
                }
            }
            loopTiming--;
        }
        return coursesOrder;
    }
    /**
     *This method add all the units that have no prerequisite
     * @return
     */
    public ArrayList<Integer> getCoursesWithNoPrerequisite(){
        HashMap<Integer,Course> tempCourses =new HashMap<>();
        for (Map.Entry<Integer, Course> p : courses.entrySet()) {
            if(p.getValue().getPreRequisites().size()==0 && ! coursesOrder.contains(p.getKey())){
                coursesOrder.add(p.getKey());
            }
        }
        return coursesOrder;
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

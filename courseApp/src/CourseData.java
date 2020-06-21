import java.util.ArrayList;

/**
 * data structure of course data, we use web crawling method to crawling the course info from ceiba
 * the course data structure to store all course data and is callable for DB.java
 */
public class CourseData {
    // data structure of CourseData.
    String random_num, course_code,course_name,teacher,ps,location;
    int credit,duration,isMust,method,student_bound;
    ArrayList<Integer> time;

    /**
     * default constructor of courseData
     */
    public CourseData(){
    }

    /**
     * constructor of courseData
     * @param random_num
     * @param course_code
     * @param course_name
     * @param credit
     * @param duration
     * @param isMust
     * @param teacher
     * @param method
     * @param time
     * @param location
     * @param student_bound
     * @param ps
     */
    public CourseData(String random_num, String course_code, String course_name, int credit, int duration, int isMust, String teacher, int method, String time, String location, int student_bound, String ps) {
        this.random_num = random_num;
        this.course_code = course_code;
        this.course_name = course_name;
        this.teacher = teacher;
        this.time = Processor.timeStringToInt(time);
        this.location = location;
        this.ps = ps;
        this.credit = credit;
        this.duration = duration;
        this.isMust = isMust;
        this.method = method;
        this.student_bound = student_bound;
    }

    // getter methods.

    public String getRandom_num() {
        return random_num;
    }

    public String getCourse_code() {
        return course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getTeacher() {
        return teacher;
    }

    public ArrayList<Integer> getTime() {
        return time;
    }
    public String getLocation(){
        return location;
    }

    public String getPs() {
        return ps;
    }

    public int getCredit() {
        return credit;
    }

    public int getDuration() {
        return duration;
    }

    public int getIsMust() {
        return isMust;
    }

    public int getMethod() {
        return method;
    }

    public int getStudent_bound() {
        return student_bound;
    }

    public String toString(){
        return random_num;
    }
}
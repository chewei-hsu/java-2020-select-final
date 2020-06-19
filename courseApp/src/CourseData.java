import java.util.ArrayList;

public class CourseData {
    String random_num, course_code,course_name,teacher,ps,location;
    int credit,duration,isMust,method,student_bound;
    ArrayList<Integer> time;

    public CourseData(){
        course_name = "Default";
    }

    public CourseData(String random_num, String course_code, String course_name, int credit, int duration, int isMust, String teacher, int method, ArrayList<Integer> time, String location, int student_bound, String ps) {
        this.random_num = random_num;
        this.course_code = course_code;
        this.course_name = course_name;
        this.teacher = teacher;
        this.time = time;
        this.location = location;
        this.ps = ps;
        this.credit = credit;
        this.duration = duration;
        this.isMust = isMust;
        this.method = method;
        this.student_bound = student_bound;
    }

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
        return course_name;
    }
}
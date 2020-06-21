import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Processor {
    public static ArrayList<Integer> timeStringToInt(String time){
        ArrayList<Integer> timeInt = new ArrayList<Integer>();
        if(time == null){
            return timeInt;
        }
        System.out.println("Before parse : " + time);
        int currentDay, currentIndex = 0;
        while(currentIndex < time.length()){
            currentDay = parseDay(time.charAt(currentIndex));
            currentIndex++;
            while(true){
                int newIndex = currentIndex;
                if(currentIndex >= time.length()){
                    break;
                }
                int classNum;
                if(time.charAt(currentIndex) >= '0' && time.charAt(currentIndex) <= '9'){
                    classNum = time.charAt(currentIndex) - 48;
                    if(time.charAt(currentIndex) == '1' && (currentIndex+1)< time.length() && time.charAt(currentIndex+1) == '0'){
                        classNum = 10;
                        newIndex++;
                    }
                }
                else if(time.charAt(currentIndex) >= 'A' && time.charAt(currentIndex) <= 'D'){
                    classNum = time.charAt(currentIndex) - 54;
                }
                else if(time.charAt(currentIndex) == ',' || time.charAt(currentIndex) == ' '){
                    newIndex++;
                    currentIndex = newIndex;
                    continue;
                }
                else{
                    break;
                }
                timeInt.add((currentDay-1)*14+classNum);
                currentIndex = newIndex;
                currentIndex++;
            }
        }
        System.out.println("After int parse :" + timeInt);
        System.out.println("Back to string :" + timeIntToString(timeInt));
        System.out.println();
        return timeInt;
    }

    public static String timeIntToString(ArrayList<Integer> time){
        if(time.isEmpty()){
            return "請洽系所辦";
        }
        int currentIndex = 0;
        String parsedTime = "";
        for(int dayCourseCount = 14 ; dayCourseCount <= 70 ; dayCourseCount+=14){
            if(currentIndex >= time.size()){
                break;
            }
            if(time.get(currentIndex) <= dayCourseCount){
                parsedTime += parseDay(dayCourseCount/14);
                parsedTime += parseClassInt(time.get(currentIndex)%14);
                currentIndex++;
                boolean exceedIncre = false;
                while(currentIndex < time.size() && time.get(currentIndex) <= dayCourseCount){
                    parsedTime += ",";
                    parsedTime += parseClassInt(time.get(currentIndex)%14);
                    currentIndex++;
                    exceedIncre = true;
                }
                parsedTime += " ";
                if(!exceedIncre){
                    currentIndex++;
                }
            }
        }
        return parsedTime;
    }

    public static ArrayList<ArrayList<CourseData>> mappingToTableArray(ArrayList<CourseData> choosedCourse){
        ArrayList<ArrayList<CourseData>> mappingTable = new ArrayList<ArrayList<CourseData>>();
        for(int i=0;i<70;i++){
            mappingTable.add(new ArrayList<CourseData>());
        }
        for(CourseData course : choosedCourse){
            for(Integer i : course.time){
                mappingTable.get(i).add(course);
            }
        }
        return mappingTable;
    }

    public static boolean courseAddCheck(ArrayList<ArrayList<CourseData>> stats,ArrayList<Integer> times){
        boolean isFull = false;
        if(stats == null){
            return true;
        }
        for(Integer time:times){
            Debugger.showDebugMessage(stats.get(time).size()+"");
            if(stats.get(time).size()>=2){
                isFull = true;
                break;
            }
        }
        return !isFull;
    }

    private static int parseDay(char ch){
        switch(ch){
            case '一':
                return 1;
            case '二':
                return 2;
            case '三':
                return 3;
            case '四':
                return 4;
            case '五':
                return 5;
            default:
                return 0;
        }
    }

    private static char parseDay(int i){
        switch(i){
            case 1:
                return '一';
            case 2:
                return '二';
            case 3:
                return '三';
            case 4:
                return '四';
            case 5:
                return '五';
            default:
                return ' ';
        }
    }

    private static String parseClassInt(int i){
        if(i<=10){
            return Integer.toString(i);
        }
        else{
            return Character.toString(i+54);
        }
    }

    public static Color colorGenerator(String randNum){
        int parsedNum = Integer.valueOf(randNum);
        int i1 = 220-parsedNum % 50;
        int i2 = 220-(100000-parsedNum) % 50;
        int index = parsedNum % 3;
        Color result = new Color(255,255,255);
        switch (index){
            case 0:
                result = new Color(255,i1,i2);
            //    Debugger.showDebugMessage(result.toString());
                break;
            case 1:
                result = new Color(i1,255,i2);
                break;
            case 2:
                result = new Color(i1,i2,255);
                break;
            default:
                result = new Color(255,255,255);
                break;
        }
        return result;
    }

    public static void autoChooseCourse(String semester) throws NullPointerException {
        ArrayList<CourseData> cdal = new ArrayList<CourseData>(DB.getCourse(null,semester,0));
        Collections.shuffle(cdal);
        for(int j = 0 ; j<cdal.size() ; j++){
            CourseData cd = cdal.get(j);
            System.out.println("Checking for : "+cd.course_name);
            if(home.choosedCourse.contains(cd) || cd.time.isEmpty()){
                cdal.remove(cd);
                continue;
            }
            boolean conflict = false;
            for(int courseTime : cd.time){
                if(home.courseStats.get(courseTime).size() != 0){
                    conflict = true;
                    cdal.remove(cd);
                    break;
                }
            }
            if(conflict){
                continue;
            }
            home.choosedCourse.add(cd);
            home.courseStats = mappingToTableArray(home.choosedCourse);
            System.out.println("Added : "+cd.course_name);
            System.out.println(home.choosedCourse);
            cdal.remove(cd);
        }
    }
}

import java.util.ArrayList;

public class Processor {

    public static ArrayList<Integer> timeStringToInt(String time){
        ArrayList<Integer> timeInt = new ArrayList<Integer>();
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
                    System.out.println("xxxx");
                    break;
                }
                timeInt.add((currentDay-1)*14+classNum);
                currentIndex = newIndex;
                currentIndex++;
            }
        }
        return timeInt;
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
}

import java.util.ArrayList;

public class Processor {
    public static ArrayList<Integer> timeWordToInt(String time){
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
                if(time.charAt(currentIndex) >= 48 && time.charAt(currentIndex) <= 57){
                    classNum = time.charAt(currentDay) - 48;
                    if(time.charAt(currentIndex) == 49 && (currentIndex+1)< time.length() && time.charAt(currentIndex+1) == 48){
                        classNum = 10;
                        newIndex++;
                    }
                }
                else if(time.charAt(currentIndex) >= 65 && time.charAt(currentIndex) <= 68){
                    classNum = time.charAt(currentDay) - 54;
                }
                else if(time.charAt(currentIndex) == 44 && time.charAt(currentIndex) == 32){
                    newIndex++;
                    continue;
                }
                else{
                    break;
                }
                System.out.println(currentDay+" "+classNum);
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

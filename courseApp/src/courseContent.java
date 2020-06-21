import javax.swing.*;
import java.awt.*;

public class courseContent {
    private JPanel content = new JPanel();
    private JTextArea name1 = new JTextArea();
    private JTextArea name2 = new JTextArea();
    private String courseName1 = "Default";
    private String courseName2 = "Default";
    private CourseData CD1;
    private CourseData CD2;
    private String[] trimmedTime = new String[3];
    public courseContent(){
        courseName1 = "Default";
        name1.setText(courseName1);
    }
    public courseContent(CourseData i){
        if(i.getCourse_name().length() > 6){
            courseName1 = i.getCourse_name().substring(0,5)+"..";
        }
        else {
            courseName1 = i.getCourse_name();
        }
        name1.setText(courseName1);
        name1.setToolTipText(i.getCourse_name()+" ( "+(timeAnalyzer(i)? trimmedTime[0]+" "+trimmedTime[1]:trimmedTime[0])+" )");
        content.removeAll();
        content.add(name1);
        CD1 = i;
    }
    public courseContent(CourseData i,CourseData j){
        if(i.getCourse_name().length() > 6){
            courseName1 = i.getCourse_name().substring(0,5)+"..";
        }
        else {
            courseName1 = i.getCourse_name();
        }
        if(i.getCourse_name().length() > 6){
            courseName2 = j.getCourse_name().substring(0,5)+"..";
        }
        else {
            courseName2 = j.getCourse_name();
        }
        CD1 = i;
        CD2 = j;
        name2.setFont(new Font("Taipei Sans TC Beta",Font.BOLD,16));
        name2.setBackground(Processor.colorGenerator(j.getRandom_num()));
        name2.setMinimumSize(new Dimension(100,20));
        name2.setPreferredSize(new Dimension(100,20));
        name2.setMargin(new Insets(1,5,1,5));
        name2.setEditable(false);
        name1.setText(courseName1);
        name1.setToolTipText(i.getCourse_name());
        name2.setText(courseName2);
        name1.setToolTipText(j.getCourse_name()+" ( "+(timeAnalyzer(j)? trimmedTime[0]+" "+trimmedTime[1]:trimmedTime[0])+" )");
        content.removeAll();
        content.add(name1);
        content.add(name2);
    }
    public boolean timeAnalyzer(CourseData courseData){
        String rawTimeString = Processor.timeIntToString(courseData.getTime());

        boolean isDoubleTime = false;
        rawTimeString = rawTimeString.trim();
        if(rawTimeString.contains(" ")){
            trimmedTime = rawTimeString.split(" ");
            return true;
        }
        else {
            trimmedTime[0] = rawTimeString;
        }
        return false;
    }

    public JPanel getPanel(){
        name1.setFont(new Font("Taipei Sans TC Beta",Font.BOLD,16));
        name1.setBackground(Processor.colorGenerator(CD1.getRandom_num()));
        name1.setMinimumSize(new Dimension(100,20));
        name1.setPreferredSize(new Dimension(100,20));
        name1.setMargin(new Insets(1,5,1,5));
        name1.setEditable(false);
        return content;
    }

    public JPanel getPanel(boolean autoGenerateCourse){
        name1.setFont(new Font("Taipei Sans TC Beta",Font.BOLD,16));
        name1.setBackground(Color.MAGENTA);
        name1.setMinimumSize(new Dimension(100,20));
        name1.setPreferredSize(new Dimension(100,20));
        name1.setMargin(new Insets(1,5,1,5));
        name1.setEditable(false);
        return content;
    }
}

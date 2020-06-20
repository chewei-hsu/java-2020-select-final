import javax.swing.*;
import java.awt.*;

public class courseContent {
    private JPanel content = new JPanel();
    private JTextArea name1 = new JTextArea();
    private JTextArea name2 = new JTextArea();
    private String courseName1 = "Default";
    private String courseName2 = "Default";
    public courseContent(){
        courseName1 = "Default";
        name1.setText(courseName1);
    }
    public courseContent(String i){
        if(i.length() > 6){
            courseName1 = i.substring(0,5)+"..";
            Debugger.showDebugMessage(i.substring(0,5)+"..");
        }
        else {
            courseName1 = i;
        }
        name1.setText(courseName1);
        content.add(name1);
    }
    public courseContent(String i,String j){
        if(i.length() > 6){
            courseName1 = i.substring(0,5)+"..";
            Debugger.showDebugMessage(i.substring(0,5)+"..");
        }
        else {
            courseName1 = i;
        }
        if(i.length() > 6){
            courseName2 = j.substring(0,5)+"..";
            Debugger.showDebugMessage(j.substring(0,5)+"..");
        }
        else {
            courseName2 = j;
        }
        name1.setText(courseName1);
        name2.setText(courseName2);
        content.add(name1);
        content.add(name2);
    }

    public JPanel getPanel(){
        name1.setFont(new Font("Taipei Sans TC Beta",Font.BOLD,16));
        name1.setBackground(Color.RED);
        name1.setMinimumSize(new Dimension(100,20));
        name1.setPreferredSize(new Dimension(100,20));
        name1.setMargin(new Insets(1,5,1,5));
        name1.setEditable(false);
        name2.setFont(new Font("Taipei Sans TC Beta",Font.BOLD,16));
        name2.setBackground(Color.RED);
        name2.setMinimumSize(new Dimension(100,20));
        name2.setPreferredSize(new Dimension(100,20));
        name2.setMargin(new Insets(1,5,1,5));
        name2.setEditable(false);
        content.setBackground(Color.RED);
        return content;
    }
}

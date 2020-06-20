import javax.swing.*;
import java.awt.*;

public class courseContent {
    private JPanel content = new JPanel();
    private JTextArea name = new JTextArea();
    private String courseName = "Default";
    public courseContent(){
        courseName = "Default";
        name.setText(courseName);
    }
    public courseContent(String i){
        courseName = i;
        name.setText(courseName);
    }
    public JPanel getPanel(){
        content.add(name);
        return content;
    }
}

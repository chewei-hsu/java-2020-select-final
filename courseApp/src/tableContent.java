import javax.swing.*;
import java.awt.*;

public class tableContent {
    private JPanel content;
    private JTextArea name;
    private String courseName = "Default";
    public tableContent(){
        courseName = "Default";
    }
    public tableContent(String i){
        courseName = i;
    }
    public JPanel getPanel(){
        return content;
    }

    private void createUIComponents() {
        name = new JTextArea();
        name.setText(courseName);
        name.setBackground(Color.RED);
    }
}

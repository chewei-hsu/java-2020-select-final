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
        name.setFont(new Font("Taipei Sans TC Beta",Font.BOLD,16));
        name.setBackground(Color.RED);
        name.setMinimumSize(new Dimension(100,60));
        name.setPreferredSize(new Dimension(100,60));
        name.setLineWrap(true);
        content.add(name);
        content.setBackground(Color.RED);
        return content;
    }
}

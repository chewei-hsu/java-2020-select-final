import javax.swing.*;
import java.awt.*;

public class courseContent {
    private JPanel content = new JPanel();
    private JTextArea name = new JTextArea();
    private String courseName = "Default";
    public courseContent(){
        name.setText("Default");
        Debugger.showDebugMessage("Set name to: Default");
    }
    public courseContent(String i){
        name.setText(i);
        Debugger.showDebugMessage("Set name to: "+i);
    }
    public JPanel getPanel(){
        content.add(name);
        return content;
    }
}

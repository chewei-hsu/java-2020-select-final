import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class courseTable {
    private JPanel panel1;
    private JPanel realPut;
    private JPanel holder1_1;
    private JPanel holder1_2;
    private JPanel holder1_3;
    private JPanel holder1_4;
    private JPanel holder1_5;
    private JPanel holder1_6;
    private JPanel holder1_7;
    private JPanel holder1_8;
    private JPanel holder1_9;
    private JPanel holder1_10;
    private JPanel holder1_11;
    private JPanel holder1_12;
    private JPanel holder1_13;
    private JPanel holder1_14;
    private JPanel holder2_1;
    private JPanel holder2_2;
    private JPanel holder2_3;
    private JPanel holder2_4;
    private JPanel holder2_5;
    private JPanel holder2_6;
    private JPanel holder2_7;
    private JPanel holder2_8;
    private JPanel holder2_9;
    private JPanel holder2_10;
    private JPanel holder2_11;
    private JPanel holder2_12;
    private JPanel holder2_13;
    private JPanel holder2_14;
    private JPanel holder3_1;
    private JPanel holder3_2;
    private JPanel holder3_3;
    private JPanel holder3_4;
    private JPanel holder3_5;
    private JPanel holder3_6;
    private JPanel holder3_7;
    private JPanel holder3_8;
    private JPanel holder3_9;
    private JPanel holder3_10;
    private JPanel holder3_11;
    private JPanel holder3_12;
    private JPanel holder3_13;
    private JPanel holder3_14;
    private JPanel holder4_1;
    private JPanel holder4_2;
    private JPanel holder4_3;
    private JPanel holder4_4;
    private JPanel holder4_5;
    private JPanel holder4_6;
    private JPanel holder4_7;
    private JPanel holder4_8;
    private JPanel holder4_9;
    private JPanel holder4_10;
    private JPanel holder4_11;
    private JPanel holder4_12;
    private JPanel holder4_13;
    private JPanel holder4_14;
    private JPanel holder5_1;
    private JPanel holder5_2;
    private JPanel holder5_3;
    private JPanel holder5_4;
    private JPanel holder5_5;
    private JPanel holder5_6;
    private JPanel holder5_7;
    private JPanel holder5_8;
    private JPanel holder5_9;
    private JPanel holder5_10;
    private JPanel holder5_11;
    private JPanel holder5_12;
    private JPanel holder5_13;
    private JPanel holder5_14;
    private JTextArea text11;
    private JTextArea text12;
    private JTextArea text13;
    private JTextArea text14;
    private JTextArea text15;
    private JTextArea text16;
    private JTextArea text17;
    private JTextArea text18;
    private JTextArea text19;
    private JTextArea text110;
    private JTextArea text111;
    private JTextArea text112;
    private JTextArea text113;
    private JTextArea text114;
    public JPanel[][] holderArray;
    public courseTable(){

    }
    public courseTable(ArrayList<ArrayList<CourseData>> mappingTable){
        for(int i = 0;i<70;i++){
            if(home.courseStats.get(i).size()>0)
                Debugger.showDebugMessage("[TABLE] Time: "+i+" Name: "+home.courseStats.get(i).get(0).getCourse_name());
        }
    }
    public JPanel getPanel(){
        tableRender();
        return panel1;
    }
    public void tableRender(){
        String[] panelTemp = new String[70];
        Debugger.showDebugMessage("Created");
        for(int i=0;i<70;i++){
            if(home.courseStats == null){
                panelTemp[i] = "";
                Debugger.showDebugMessage("Dataset; "+i+" No data!");
            }
            else if(home.courseStats.get(i).size()==0){
                panelTemp[i] = "";
                Debugger.showDebugMessage("Dataset; "+i+" This time is Empty!");
            }
            else if(home.courseStats.get(i).size()==1) {
                panelTemp[i] = home.courseStats.get(i).get(0).getCourse_name();
                Debugger.showDebugMessage("Dataset; "+i+" This time is "+home.courseStats.get(i).get(0).getCourse_name());
            }
            else {
                panelTemp[i] = home.courseStats.get(i).get(0).getCourse_name();
                Debugger.showDebugMessage("Dataset; "+i+" This time is "+home.courseStats.get(i).get(0).getCourse_name());
            }
        }
        text11.setText(panelTemp[0]);
        text12.setText(panelTemp[1]);
    }

    private void createUIComponents() {
        try {
            tableRender();
        }catch (Exception e){
            Debugger.showDebugMessage(e.toString());
        }
    }
}


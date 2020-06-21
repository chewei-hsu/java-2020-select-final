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
    public courseTable(){

    }
    public courseTable(ArrayList<ArrayList<CourseData>> mappingTable){
        for(int i = 0;i<70;i++){
            if(home.courseStats.get(i).size()>0){
                //Debugger.showDebugMessage("[TABLE] Time: "+i+" Name: "+home.courseStats.get(i).get(0).getCourse_name())
            }
        }
    }
    public JPanel getPanel(){
        return panel1;
    }
    public void tableRender(){
        JPanel[] panelTemp = new JPanel[70];
        for(int i=0;i<70;i++){
            if(home.courseStats == null){
                panelTemp[i] = new JPanel();
                //Debugger.showDebugMessage("Dataset; "+i+" No data!");
            }
            else if(home.courseStats.get(i).size()==0){
                panelTemp[i] = new JPanel();
                //Debugger.showDebugMessage("Dataset; "+i+" This time is Empty!");
            }
            else if(home.courseStats.get(i).size()==1) {
                panelTemp[i] = new courseContent(home.courseStats.get(i).get(0)).getPanel();
                //Debugger.showDebugMessage("Dataset; "+i+" This time is "+home.courseStats.get(i).get(0).getCourse_name());
            }
            else {
                panelTemp[i] = new courseContent(home.courseStats.get(i).get(0),home.courseStats.get(i).get(1)).getPanel();
                //Debugger.showDebugMessage("Dataset; "+i+" This time is "+home.courseStats.get(i).get(0).getCourse_name());
            }
        }

        holder1_1 = panelTemp[0];
        holder1_2 = panelTemp[1];
        holder1_3 = panelTemp[2];
        holder1_4 = panelTemp[3];
        holder1_5 = panelTemp[4];
        holder1_6 = panelTemp[5];
        holder1_7 = panelTemp[6];
        holder1_8 = panelTemp[7];
        holder1_9 = panelTemp[8];
        holder1_10 = panelTemp[9];
        holder1_11 = panelTemp[10];
        holder1_12 = panelTemp[11];
        holder1_13 = panelTemp[12];
        holder1_14 = panelTemp[13];
        holder2_1 = panelTemp[14];
        holder2_2 = panelTemp[15];
        holder2_3 = panelTemp[16];
        holder2_4 = panelTemp[17];
        holder2_5 = panelTemp[18];
        holder2_6 = panelTemp[19];
        holder2_7 = panelTemp[20];
        holder2_8 = panelTemp[21];
        holder2_9 = panelTemp[22];
        holder2_10 = panelTemp[23];
        holder2_11 = panelTemp[24];
        holder2_12 = panelTemp[25];
        holder2_13 = panelTemp[26];
        holder2_14 = panelTemp[27];
        holder3_1 = panelTemp[28];
        holder3_2 = panelTemp[29];
        holder3_3 = panelTemp[30];
        holder3_4 = panelTemp[31];
        holder3_5 = panelTemp[32];
        holder3_6 = panelTemp[33];
        holder3_7 = panelTemp[34];
        holder3_8 = panelTemp[35];
        holder3_9 = panelTemp[36];
        holder3_10 = panelTemp[37];
        holder3_11 = panelTemp[38];
        holder3_12 = panelTemp[39];
        holder3_13 = panelTemp[40];
        holder3_14 = panelTemp[41];
        holder4_1 = panelTemp[42];
        holder4_2 = panelTemp[43];
        holder4_3 = panelTemp[44];
        holder4_4 = panelTemp[45];
        holder4_5 = panelTemp[46];
        holder4_6 = panelTemp[47];
        holder4_7 = panelTemp[48];
        holder4_8 = panelTemp[49];
        holder4_9 = panelTemp[50];
        holder4_10 = panelTemp[51];
        holder4_11 = panelTemp[52];
        holder4_12 = panelTemp[53];
        holder4_13 = panelTemp[54];
        holder4_14 = panelTemp[55];
        holder5_1 = panelTemp[56];
        holder5_2 = panelTemp[57];
        holder5_3 = panelTemp[58];
        holder5_4 = panelTemp[59];
        holder5_5 = panelTemp[60];
        holder5_6 = panelTemp[61];
        holder5_7 = panelTemp[62];
        holder5_8 = panelTemp[63];
        holder5_9 = panelTemp[64];
        holder5_10 = panelTemp[65];
        holder5_11 = panelTemp[66];
        holder5_12 = panelTemp[67];
        holder5_13 = panelTemp[68];
        holder5_14 = panelTemp[69];
    }

    private void createUIComponents() {
        tableRender();
    }
}


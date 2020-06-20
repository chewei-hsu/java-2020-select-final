import javax.swing.*;
import java.awt.*;
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
    public JPanel[][] holderArray = null;
    public JPanel getPanel(){
        holderArray= new JPanel[][]{
                {holder1_1,holder1_2,holder1_3,holder1_4,holder1_5,holder1_6,holder1_7,holder1_8,holder1_9,holder1_10,holder1_11,holder1_12,holder1_13,holder1_14},
                {holder2_1,holder2_2,holder2_3,holder2_4,holder2_5,holder2_6,holder2_7,holder2_8,holder2_9,holder2_10,holder2_11,holder2_12,holder2_13,holder2_14},
                {holder3_1,holder3_2,holder3_3,holder3_4,holder3_5,holder3_6,holder3_7,holder3_8,holder3_9,holder3_10,holder3_11,holder3_12,holder3_13,holder3_14},
                {holder4_1,holder4_2,holder4_3,holder4_4,holder4_5,holder4_6,holder4_7,holder4_8,holder4_9,holder4_10,holder4_11,holder4_12,holder4_13,holder4_14},
                {holder5_1,holder5_2,holder5_3,holder5_4,holder5_5,holder5_6,holder5_7,holder5_8,holder5_9,holder5_10,holder5_11,holder5_12,holder5_13,holder5_14}
        };
        return panel1;
    }
    public void tableRender(ArrayList<CourseData>[][] mappingTable){
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 14 ; j++){
                if(mappingTable[i][j] != null){

                }
            }
        }
    }
}

class holder{
    public JPanel panel;
    public
}

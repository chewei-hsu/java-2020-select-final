import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JListCustomRenderer extends JFrame{
    private ArrayList<CourseData> displayData = new ArrayList<CourseData>();
    private static CourseData target;
    public static JPanel frame = new JPanel(new BorderLayout());
    public JListCustomRenderer(){

    }
    public JPanel createPanel(ArrayList<CourseData> CD){
        if(CD != null){
            Debugger.showDebugMessage(CD.size()+"");
            Debugger.showDebugMessage("CD loaded!");
            displayData = CD;
            Debugger.showDebugMessage(displayData.size()+"");
        }
        //JPanel frame = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JList jlist = createList();
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList<String> theList = (JList) mouseEvent.getSource();
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        Debugger.showDebugMessage("Single-clicked on: " + o);
                        for(int i=0; i<displayData.size(); i++){
                            if(displayData.get(i).getCourse_name().equals(o.toString())){
                                target = displayData.get(i);
                                break;
                            }
                        }
                    }
                if (mouseEvent.getClickCount() == 2) {
                    index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        Debugger.showDebugMessage("Double-clicked on: " + o);
                        target = new CourseData();
                        for(int i=0; i<displayData.size(); i++){
                            if(displayData.get(i).getCourse_name().equals(o.toString())){
                                target = displayData.get(i);
                                break;
                            }
                        }
                    }
                }
            }
        };
        jlist.addMouseListener(mouseListener);
        panel.setBorder(new EmptyBorder(3,3,3,3));
        panel.setBackground(new Color(80, 80, 80));
        panel.add(new JScrollPane(jlist),BorderLayout.CENTER);
        frame.add(panel);
        return frame;
    }
    public JList<CourseData> createList(){
        DefaultListModel<CourseData> model = new DefaultListModel<CourseData>();
        for(CourseData val : displayData){
            model.addElement(val);
        }
        JList<CourseData> list = new JList<CourseData>(model);
        list.setCellRenderer(new CourseRenderer());
        Debugger.showDebugMessage("LIST created.");
        return list;
    }
    public static CourseData getTarget(){
        return target;
    }
}
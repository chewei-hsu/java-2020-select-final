
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JListCustomRenderer extends JFrame{
    private ArrayList<CourseData> displayData = new ArrayList<CourseData>();
    public JListCustomRenderer(){

    }
    public JPanel createPanel(ArrayList<CourseData> CD){
        if(CD != null){
            Debugger.showDebugMessage("CD loaded!");
            displayData = CD;
        }
        JPanel frame = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JList jlist = createList();
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList<String> theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        Debugger.showDebugMessage("Double-clicked on: " + o);
                        CourseData target = new CourseData();
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
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setBackground(new Color(21,188,163));
        panel.add(new JScrollPane(jlist),BorderLayout.CENTER);
        frame.add(panel);
        return frame;
    }
    public JList<CourseData> createList(){
        DefaultListModel<CourseData> model = new DefaultListModel<CourseData>();
        for(CourseData val : displayData)
            model.addElement(val);
        JList<CourseData> list = new JList<CourseData>(model);
        list.setCellRenderer(new CourseRenderer());
        Debugger.showDebugMessage("LIST created.");
        return list;
    }
}

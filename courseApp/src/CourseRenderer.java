import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CourseRenderer extends JPanel implements ListCellRenderer<CourseData> {
    private JLabel lbTitle = new JLabel();
    private JLabel lbCourseCode = new JLabel();
    private JLabel lbTeacher = new JLabel();
    private JLabel lbTime1 = new JLabel();
    private JLabel lbTime2 = new JLabel();
    private Color CYAN = new Color(21, 188, 163);
    private Color LIGHT_CYAN = new Color(179, 241, 236);
    private Color LIGHT_GRAY = new Color(237, 237, 237);
    public CourseRenderer(){
        setBackground(LIGHT_CYAN);
        setLayout(new BorderLayout(0,0));
        setBorder(new EmptyBorder(0,0,3,0));
        JPanel panelLeft = new JPanel(new GridLayout(0,1));
        JPanel panelRight = new JPanel(new GridLayout(0,1));
        panelLeft.add(lbTitle);
        panelLeft.add(lbTime2);
        panelLeft.add(lbCourseCode);
        panelRight.add(lbTeacher);
        panelRight.add(lbTime1);
        add(panelRight, BorderLayout.EAST);
        add(panelLeft, BorderLayout.CENTER);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends CourseData> list, CourseData courseData, int index, boolean isSelected, boolean cellHasFocus) {


        lbTitle.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        lbTitle.setText(courseData.getCourse_name());
        lbTime2.setText("  課程編號:  "+ courseData.getCourse_code());
        lbTime2.setForeground(Color.GRAY);
        lbTime2.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        lbTeacher.setForeground(Color.red);
        lbTeacher.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        lbTeacher.setText(courseData.getTeacher());
        lbTeacher.setHorizontalAlignment(JTextField.RIGHT);
        lbTeacher.setVerticalAlignment(JTextField.TOP);
        lbTime1.setForeground(Color.BLUE);
        lbTime1.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        lbTime1.setText("時間一");
        lbTime1.setHorizontalAlignment(JTextField.RIGHT);
        lbTime1.setVerticalAlignment(JTextField.TOP);
        lbTitle.setOpaque(true);
        lbTime1.setText("時間二");
        lbTime2.setOpaque(true);
        lbCourseCode.setOpaque(true);
        lbTime1.setOpaque(true);
        lbTeacher.setOpaque(true);

        // when select item
        if (isSelected) {
            lbTitle.setBackground(list.getSelectionBackground());
            lbCourseCode.setBackground(list.getSelectionBackground());
            lbTime2.setBackground(list.getSelectionBackground());
            lbTime1.setBackground(list.getSelectionBackground());
            lbTeacher.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else { // when don't select
            lbTitle.setBackground(LIGHT_GRAY);
            lbCourseCode.setBackground(LIGHT_GRAY);
            lbTime2.setBackground(LIGHT_GRAY);
            lbTime1.setBackground(LIGHT_GRAY);
            lbTeacher.setBackground(LIGHT_GRAY);
            setBackground(CYAN);
        }
        return this;
    }
}

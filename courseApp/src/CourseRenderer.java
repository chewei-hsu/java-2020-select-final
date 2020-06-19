import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CourseRenderer extends JPanel implements ListCellRenderer<CourseData> {
    private JLabel lbTitle = new JLabel();
    private JLabel lbCourseCode = new JLabel();
    private JLabel lbTeacher = new JLabel();
    private JLabel lbTime1 = new JLabel();
    private JLabel lbTime2 = new JLabel();
    private Color CYAN = new Color(21, 188, 163);
    private Color LIGHT_CYAN = new Color(179, 241, 236);
    private Color LIGHT_GRAY = new Color(237, 237, 237);
    private Color COLOR_PRIMARY = new Color(0, 45, 98);
    private Color COLOR_SECONDARY = new Color(82, 117, 129, 155);
    private Color COLOR_TIME = new Color(203, 127, 35);
    public CourseRenderer(){
        setBackground(Color.GRAY);
        setLayout(new BorderLayout(0,0));
        setBorder(new EmptyBorder(0,0,3,0));
        JPanel panelLeft = new JPanel(new GridLayout(0,1));
        JPanel panelRight = new JPanel(new GridLayout(0,1));
        JPanel rightHolder = new JPanel(new BorderLayout(5,5));
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBorder(new EmptyBorder(5,5,5,5));
        panelLeft.add(lbTitle);
        panelLeft.add(lbTeacher);
        panelLeft.add(lbCourseCode);
        panelRight.add(lbTime1);
        panelRight.add(lbTime2);
        panelRight.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        LineBorder border = new LineBorder(COLOR_TIME, 3);
        rightHolder.setBorder(border);
        rightHolder.add(panelRight);
        holder.add(rightHolder, BorderLayout.EAST);
        holder.add(panelLeft, BorderLayout.CENTER);
        add(holder);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends CourseData> list, CourseData courseData, int index, boolean isSelected, boolean cellHasFocus) {
        Debugger.showDebugMessage("getListCellRComp");
        lbTitle.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        lbTitle.setText(courseData.getCourse_name());
        lbTitle.setOpaque(true);
        lbTitle.setForeground(Color.DARK_GRAY);

        lbTeacher.setForeground(COLOR_PRIMARY);
        lbTeacher.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        lbTeacher.setText(courseData.getTeacher());
        lbTeacher.setHorizontalAlignment(JTextField.LEFT);
        lbTeacher.setVerticalAlignment(JTextField.CENTER);
        lbTeacher.setOpaque(true);

        lbCourseCode.setText(courseData.getCourse_code());
        lbCourseCode.setOpaque(true);
        lbCourseCode.setFont(new Font(Font.DIALOG,Font.ITALIC,12));
        lbCourseCode.setForeground(COLOR_SECONDARY);

        lbTime1.setOpaque(true);
        lbTime1.setForeground(COLOR_TIME);
        lbTime1.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        lbTime1.setText("時間一");
        lbTime1.setHorizontalAlignment(JTextField.RIGHT);
        lbTime1.setVerticalAlignment(JTextField.TOP);

        lbTime2.setText(courseData.getCourse_code());
        lbTime2.setForeground(COLOR_TIME);
        lbTime2.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        lbTime1.setHorizontalAlignment(JTextField.RIGHT);
        lbTime1.setVerticalAlignment(JTextField.TOP);
        lbTime2.setText("時間二");
        lbTime2.setOpaque(true);




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
            setBackground(Color.GRAY);
        }
        return this;
    }
}

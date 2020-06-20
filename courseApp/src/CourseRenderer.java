import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CourseRenderer extends JPanel implements ListCellRenderer<CourseData> {
    private JLabel lbTitle = new JLabel();
    private JLabel lbCourseCode = new JLabel();
    private JLabel lbTeacher = new JLabel();
    private JLabel lbTime1 = new JLabel();
    private Color CYAN = new Color(21, 188, 163);
    private Color LIGHT_CYAN = new Color(179, 241, 236);
    private Color LIGHT_GRAY = new Color(237, 237, 237);
    private Color COLOR_PRIMARY = new Color(0, 45, 98);
    private Color COLOR_SECONDARY = new Color(82, 117, 129, 155);
    private Color COLOR_TIME = new Color(203, 127, 35);
    private Color COLOR_SELECTED = new Color(255, 236, 204);
    private JPanel panelRight = new JPanel(new GridLayout(0,1));
    private JPanel panelLeft = new JPanel(new GridLayout(0,1));
    private JPanel rightHolder = new JPanel(new BorderLayout(5,5));
    private JPanel holder = new JPanel(new BorderLayout());
    public CourseRenderer(){
        setBackground(Color.GRAY);
        setLayout(new BorderLayout(0,0));
        setBorder(new EmptyBorder(0,0,3,0));
        holder.setBorder(new EmptyBorder(5,5,5,5));
        panelLeft.add(lbTitle);
        panelLeft.add(lbTeacher);
        panelLeft.add(lbCourseCode);
        panelRight.add(lbTime1);
        panelRight.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        LineBorder border = new LineBorder(COLOR_TIME, 3);
        rightHolder.setBorder(border);
        rightHolder.add(panelRight);
        holder.add(rightHolder, BorderLayout.EAST);
        holder.add(panelLeft, BorderLayout.CENTER);
        add(holder);
    }
    public boolean selectCondition(CourseData cd){
        boolean isExist = false;
        for(CourseData item:home.choosedCourse){
            if(item.getRandom_num().equals(cd.random_num)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends CourseData> list, CourseData courseData, int index, boolean isSelected, boolean cellHasFocus) {
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
        String rawTimeString = Processor.timeIntToString(courseData.getTime());
        String[] trimmedTime = new String[3];
        boolean isDoubleTime = false;
        rawTimeString = rawTimeString.trim();
        if(rawTimeString.contains(" ")){
            isDoubleTime = true;
            trimmedTime = rawTimeString.split(" ");
        }
        else {
            isDoubleTime = false;
        }
        lbTime1.setOpaque(true);
        lbTime1.setForeground(COLOR_TIME);
        lbTime1.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        lbTime1.setText(isDoubleTime? ("<html>"+trimmedTime[0]+"<br>"+trimmedTime[1]+"</html>"):rawTimeString);
        lbTime1.setHorizontalAlignment(JTextField.CENTER);
        lbTime1.setVerticalAlignment(JTextField.CENTER);



        // when select item
        if (isSelected) {
            lbTitle.setBackground(list.getSelectionBackground());
            lbCourseCode.setBackground(list.getSelectionBackground());
            lbTime1.setBackground(list.getSelectionBackground());
            lbTeacher.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else if(!selectCondition(courseData)){ // when don't select
            lbTitle.setBackground(LIGHT_GRAY);
            lbCourseCode.setBackground(LIGHT_GRAY);
            lbTime1.setBackground(LIGHT_GRAY);
            lbTeacher.setBackground(LIGHT_GRAY);
            setBackground(Color.GRAY);
        }
        else{
            lbTitle.setBackground(Processor.colorGenerator(courseData.getRandom_num()));
            lbCourseCode.setBackground(Processor.colorGenerator(courseData.getRandom_num()));
            lbTime1.setBackground(Processor.colorGenerator(courseData.getRandom_num()));
            lbTeacher.setBackground(Processor.colorGenerator(courseData.getRandom_num()));
            setBackground(Processor.colorGenerator(courseData.getRandom_num()));
        }
        return this;
    }
}
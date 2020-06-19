import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CourseRenderer extends JPanel implements ListCellRenderer<CourseData> {
    private JLabel lbTitle = new JLabel();
    private JLabel lbCourseName = new JLabel();
    private JLabel lbPrice = new JLabel();
    private JLabel lbAmount = new JLabel();
    private JLabel lbCat = new JLabel();
    private JButton btnMore = new JButton();
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
        panelLeft.add(lbCat);
        panelLeft.add(lbCourseName);
        panelRight.add(lbPrice);
        panelRight.add(lbAmount);
        add(panelRight, BorderLayout.EAST);
        add(panelLeft, BorderLayout.CENTER);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends CourseData> list, CourseData courseData, int index, boolean isSelected, boolean cellHasFocus) {


        lbTitle.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        lbTitle.setText(courseData.getCourse_name());
        lbCat.setText("  課程編號:  "+ courseData.getCourse_code());
        lbCat.setForeground(Color.GRAY);
        lbCat.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        lbPrice.setForeground(Color.red);
        lbPrice.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        lbPrice.setText("$"+courseData.getCheapestPrice()+" 起 ");
        lbPrice.setHorizontalAlignment(JTextField.RIGHT);
        lbPrice.setVerticalAlignment(JTextField.TOP);
        lbAmount.setForeground(Color.BLUE);
        lbAmount.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        lbAmount.setText("最低 "+courseData.getLowestBound()+" 人出團  ");
        lbAmount.setHorizontalAlignment(JTextField.RIGHT);
        lbAmount.setVerticalAlignment(JTextField.TOP);
        lbTitle.setOpaque(true);
        lbCat.setOpaque(true);
        lbIcon.setOpaque(true);
        lbCourseName.setOpaque(true);
        lbAmount.setOpaque(true);
        lbPrice.setOpaque(true);

        // when select item
        if (isSelected) {
            lbTitle.setBackground(list.getSelectionBackground());
            lbCourseName.setBackground(list.getSelectionBackground());
            lbCat.setBackground(list.getSelectionBackground());
            lbIcon.setBackground(list.getSelectionBackground());
            lbAmount.setBackground(list.getSelectionBackground());
            lbPrice.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else { // when don't select
            lbTitle.setBackground(LIGHT_GRAY);
            lbCourseName.setBackground(LIGHT_GRAY);
            lbCat.setBackground(LIGHT_GRAY);
            lbIcon.setBackground(LIGHT_GRAY);
            lbAmount.setBackground(LIGHT_GRAY);
            lbPrice.setBackground(LIGHT_GRAY);
            setBackground(CYAN);
        }
        return this;
    }
}

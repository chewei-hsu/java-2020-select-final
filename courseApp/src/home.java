import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class home {
    private static JFrame frame = new JFrame("Course"); // 設定視窗標題

    private JPanel searchBarAndBtn;
    private JPanel searchResult;
    //private JPanel resultHolder = new JPanel();
    private JPanel selected;
    private JPanel panel1;
    private JTextField searchField;
    private JButton btnLucky;
    private JButton btnSearch;
    private JPanel btnHolder;
    private JPanel resultHolder;
    private JPanel notFoundPanel;
    private JLabel title;
    private JPanel searchHolder;
    private JComboBox semesterSelect;
    private String searchTemp = "";
    private String searchCourse = null;
    private CardLayout layout;
    private JListCustomRenderer resultList = new JListCustomRenderer();;
    public static String metalUI = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static CourseData detailData = new CourseData();
    public home(String search){
        super();
        searchCourse = search;
    }
    public home() {
        searchResult = resultList.createPanel(DB.getCourse(null,"108-2"));
        selected.setBorder(new LineBorder(Color.GRAY, 3));
        resultHolder.add(searchResult,"r");
        resultHolder.add(notFoundPanel,"n");
        layout = (CardLayout)resultHolder.getLayout();
        layout.show(resultHolder,"r");
        MouseMotionListener clickListener = new MouseAdapter() {
            public void mouseMoved(MouseEvent me) {
                //detailData = resultList.getTarget();
                if(detailData != null){
                    title.setText(detailData.getCourse_name());
                }

            }
        };
        frame.addMouseMotionListener(clickListener);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(searchField.getText().equals("輸入關鍵字 或 直接搜尋")){
                    searchCourse = null;
                }
                else{
                    searchCourse=searchField.getText();
                }
                System.out.println("Search for:" + searchCourse);
                ArrayList<CourseData> CD = DB.getCourse(searchCourse,semesterSelect.getSelectedItem().toString());
                if(CD.size() == 0){
                    layout.show(resultHolder,"n");
                }
                else{
                    resultHolder.remove(searchResult);
                    searchResult = null;
                    searchResult = resultList.createPanel(CD);
                    resultHolder.add(searchResult,"r");
                    layout = (CardLayout)resultHolder.getLayout();
                    layout.show(resultHolder,"r");
                }
            }
        });
        btnLucky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                searchField.setText(searchTemp);
                searchField.setForeground(new Color(8, 37, 42));
            }
        });

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (searchField.getText().equals("")) {
                    searchField.setText("輸入關鍵字 或 直接搜尋");
                    searchTemp = "";
                    searchField.setForeground(new Color(175, 175, 175));
                } else {
                    searchTemp = searchField.getText();
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel1;
    }

    public static void main(String[] args) {
        Debugger.setDebugMode(true);
        try {
            UIManager.setLookAndFeel(home.metalUI); // 使用Metal UI 模式啟動
            UIManager.setLookAndFeel(metalUI); // 使用Metal UI 模式啟動
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new home().panel1);
        frame.pack();
        frame.getContentPane().requestFocusInWindow();
        frame.setSize(1000, 800); // 設定初始視窗大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        windowSizeLimiter(frame, 1000, 800);
    }

    private static void windowSizeLimiter(JFrame frame, int width, int height) {
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Dimension size = frame.getSize();
                if (size.getWidth() < width || size.getHeight() < height) {
                    JOptionPane.showMessageDialog(null,"過小的視窗大小會影響使用體驗!","視窗大小",JOptionPane.WARNING_MESSAGE);
                    frame.setSize((int) width+100, (int) height+50);
                }
            }
        });
    }

    private void createUIComponents() {
    }
}
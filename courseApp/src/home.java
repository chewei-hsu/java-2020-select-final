import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
    private JLabel lbName;
    private JPanel searchHolder;
    private JComboBox semesterSelect;
    private JLabel lbLocation;
    private JTextPane textPs;
    private JLabel lbIsMust;
    private JLabel lbCredit;
    private JLabel lbWay;
    private JLabel lbCode;
    private JLabel lbRandNum;
    private JPanel tableCard;
    private JPanel weekdayHolder;
    private JPanel tableHolder;
    private JPanel tableDisplay;
    private String searchTemp = "";
    private String searchCourse = null;
    private CardLayout layout;
    private CardLayout tableLayout;
    private JListCustomRenderer resultList = new JListCustomRenderer();;
    public static String metalUI = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static CourseData detailData = new CourseData();
    public home(String search){
        super();
        searchCourse = search;
    }
    public home() {
        initDetailElement();
        selected.setVisible(false);
        searchResult = resultList.createPanel(DB.getCourse(null,"108-2"));
        tableDisplay = new courseTable().getPanel();
        selected.setBorder(new LineBorder(Color.GRAY, 3));
        resultHolder.add(searchResult,"r");
        resultHolder.add(notFoundPanel,"n");
        layout = (CardLayout)resultHolder.getLayout();
        tableHolder.add(tableDisplay,"t");
        tableLayout = (CardLayout)tableHolder.getLayout();
        layout.show(resultHolder,"r");
        tableLayout.show(tableHolder,"t");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                search();
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
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if(keyCode == 10){
                    //Enter button
                    search();
                }
            }
        });
    }

    public void search(){
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
        frame.setSize(1024, 900); // 設定初始視窗大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        windowSizeLimiter(frame, 1000, 900);
    }
    public void refreshDetailPanel(){
        if(detailData != null){
            selected.setVisible(true);
            lbName.setText(detailData.getCourse_name());
            lbLocation.setText(detailData.getLocation());
            lbCredit.setText(detailData.getCredit()+"");
            lbWay.setText(detailData.getMethod()+"");
            String convertedIsMust = "";
            switch (detailData.getIsMust()){
                case 2:
                    convertedIsMust = "必帶";
                    break;
                case 1:
                    convertedIsMust = "必修";
                    break;
                case 0:
                    convertedIsMust = "選修";
                    break;
                default:
                    convertedIsMust = "未知";
                    break;
            }
            lbIsMust.setText(convertedIsMust);
            lbCode.setText(detailData.getCourse_code());
            lbRandNum.setText(detailData.getRandom_num());
            if(detailData.getPs() == null){
                textPs.setText("無內容");
                textPs.setForeground(Color.GRAY);
            }
            else {
                textPs.setText(detailData.getPs());
                textPs.setForeground(new Color(0, 45, 98));
            }

        }
    }
   public void initDetailElement(){
        lbName.setText("");
        lbLocation.setText("");
        lbCredit.setText("");
        lbWay.setText("");
        lbIsMust.setText("");
        lbCode.setText("");
        lbRandNum.setText("");
        textPs.setText("");
   }


    private static void windowSizeLimiter(JFrame frame, int width, int height) {
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Dimension size = frame.getSize();
                if (size.getWidth() < width || size.getHeight() < height) {
                    frame.setSize((int) width + 50, (int) height + 25);
                    JOptionPane.showMessageDialog(null,"過小的視窗大小會影響使用體驗! \n 建議視窗大小：1024*900 以上","視窗大小警告",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void createUIComponents() {
    }

    class JListCustomRenderer extends JFrame {
        private ArrayList<CourseData> displayData = new ArrayList<CourseData>();

        public JListCustomRenderer() {

        }

        public JPanel createPanel(ArrayList<CourseData> CD) {
            if (CD != null) {
                Debugger.showDebugMessage(CD.size() + "");
                Debugger.showDebugMessage("CD loaded!");
                displayData = CD;
                Debugger.showDebugMessage(displayData.size() + "");
            }
            JPanel frame = new JPanel(new BorderLayout());
            JPanel panel = new JPanel(new BorderLayout());
            JList jlist = createList();

            MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent mouseEvent) {
                    JList<String> theList = (JList) mouseEvent.getSource();
                    if (mouseEvent.getClickCount() == 1) {
                        int index = theList.locationToIndex(mouseEvent.getPoint());
                        if (index >= 0) {
                            Object o = theList.getModel().getElementAt(index);

                            for (int i = 0; i < displayData.size(); i++) {
                                if (displayData.get(i).getCourse_name().equals(o.toString())) {
                                    detailData = displayData.get(i);
                                    refreshDetailPanel();
                                    break;
                                }
                            }
                        }
                    }
                    if (mouseEvent.getClickCount() == 2) {
                        int index = theList.locationToIndex(mouseEvent.getPoint());
                        if (index >= 0) {
                            Object o = theList.getModel().getElementAt(index);
                            Debugger.showDebugMessage("Double-clicked on: " + o);
                            for (int i = 0; i < displayData.size(); i++) {
                                if (displayData.get(i).getCourse_name().equals(o.toString())) {
                                    detailData = displayData.get(i);
                                    break;
                                }
                            }
                        }
                    }
                }
            };
            jlist.addMouseListener(mouseListener);
            panel.setBorder(new EmptyBorder(3, 3, 3, 3));
            panel.setBackground(new Color(80, 80, 80));
            panel.add(new JScrollPane(jlist), BorderLayout.CENTER);
            frame.add(panel);
            return frame;
        }

        public JList<CourseData> createList() {
            DefaultListModel<CourseData> model = new DefaultListModel<CourseData>();
            for (CourseData val : displayData) {
                Debugger.showDebugMessage("Model ADDED!");
                model.addElement(val);
            }
            JList<CourseData> list = new JList<CourseData>(model);
            list.setCellRenderer(new CourseRenderer());
            Debugger.showDebugMessage("LIST created.");
            return list;
        }
    }
}
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
    private JPanel holder1_1;
    private JComboBox isMustSelect;
    private String searchTemp = "";
    private String searchCourse = null;
    private CardLayout layout;
    private CardLayout tableLayout;
    private JListCustomRenderer resultList = new JListCustomRenderer();;
    public static String metalUI = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static CourseData detailData = new CourseData();
    public static ArrayList<CourseData> choosedCourse = new ArrayList<>();
    public static ArrayList<ArrayList<CourseData>> courseStats;
    public home(String search){
        super();
        searchCourse = search;
    }
    public int currentSemesterIndex = semesterSelect.getSelectedIndex();
    public home() {
        initDetailElement();
        selected.setVisible(false);
        searchResult = resultList.createPanel(DB.getCourse(null, semesterSelect.getSelectedItem().toString()));
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
                if(searchField.getText().equals("debug -on")){
                    Debugger.setDebugMode(true);
                }
                else if(searchField.getText().equals("debug -off")){
                    Debugger.setDebugMode(false);
                }
                else{
                    search();
                }

            }
        });
        btnLucky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(choosedCourse.size() == 0){
                    JOptionPane.showMessageDialog(null,"請先選擇至少一門課程再使用此功能","你沒有必帶嗎",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try{
                        Processor.autoChooseCourse(semesterSelect.getSelectedItem().toString());
                        refreshTable(courseStats);
                    }catch(NullPointerException ne){
                        JOptionPane.showMessageDialog(null,"請先選擇至少一門課程再使用此功能","你沒有必帶嗎",JOptionPane.ERROR_MESSAGE);
                    }
                }
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
                    if(searchField.getText().equals("debug -on")){
                        if(Debugger.getDebugMode()){
                            JOptionPane.showMessageDialog(null,"開發者模式已經是開啟狀態\n如需關閉請輸入 debug -off","開發者模式",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            int check = JOptionPane.showConfirmDialog(null,"您確定要進入開發者模式嗎?\n開發者模式可能讓程式穩定性下降或顯示敏感資訊，請小心使用。 ","開發者模式確認",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                            if(check == 0){
                                Debugger.setDebugMode(true);
                                JOptionPane.showMessageDialog(null,"已開啟開發者模式","開發者模式",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                    }
                    else if(searchField.getText().equals("debug -off")){
                        if(!Debugger.getDebugMode()){
                            JOptionPane.showMessageDialog(null,"開發者模式已經是關閉狀態\n如需開啟請輸入 debug -on","開發者模式",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            Debugger.setDebugMode(false);
                            JOptionPane.showMessageDialog(null,"已關閉開發者模式","開發者模式",JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                    else{
                        search();
                    }
                }
            }
        });

        semesterSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debugger.showDebugMessage("Attempt to change semester to : "+semesterSelect.getSelectedItem());
                int check = JOptionPane.showConfirmDialog(null,"變更搜尋學期將會清空目前的課表，確認繼續進行嗎? ","變更學期確認",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if(check == 0){
                    currentSemesterIndex = semesterSelect.getSelectedIndex();
                    choosedCourse.clear();
                    courseStats = Processor.mappingToTableArray(choosedCourse);
                    refreshTable(courseStats);
                    search();
                }
                else{
                    semesterSelect.setSelectedIndex(currentSemesterIndex);
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
            searchResult = resultList.createPanel(new ArrayList<CourseData>(CD));
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
    public void refreshTable(ArrayList<ArrayList<CourseData>> data){
        tableHolder.remove(tableDisplay);
        tableDisplay = null;
        tableDisplay = new courseTable(data).getPanel();
        tableHolder.add(tableDisplay,"t");
        tableLayout = (CardLayout)tableHolder.getLayout();
        tableLayout.show(tableHolder,"t");
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
                            Debugger.showDebugMessage("Single-clicked on: " + o);
                            for (int i = 0; i < displayData.size(); i++) {
                                if (displayData.get(i).getRandom_num().equals(o.toString())) {
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
                                if (displayData.get(i).getRandom_num().equals(o.toString())) {
                                    detailData = displayData.get(i);
                                    break;
                                }
                            }
                            boolean isExist = false;
                            CourseData target = null;
                            for(CourseData item:choosedCourse){
                                if(item.getRandom_num().equals(detailData.random_num)){
                                    isExist = true;
                                    target=item;
                                    break;
                                }
                            }
                            System.out.println(choosedCourse);
                            if(isExist){
                                choosedCourse.remove(target);
                            }
                            else{
                                if(Processor.courseAddCheck(courseStats,detailData.getTime())){
                                    choosedCourse.add(detailData);
                                }
                                else {
                                    JOptionPane.showMessageDialog(null,"新增課程與已選課程衝堂! \n 最多只允許衝堂兩堂","無法新增課程",JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            System.out.println(choosedCourse);
                            courseStats = Processor.mappingToTableArray(choosedCourse);
                            refreshTable(courseStats);
                            refreshDetailPanel();
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
                model.addElement(val);
            }
            JList<CourseData> list = new JList<CourseData>(model);
            list.setCellRenderer(new CourseRenderer());
            Debugger.showDebugMessage("LIST created.");
            return list;
        }

    }
}
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class home {
    private static JFrame frame = new JFrame("Course"); // set window title
    private JPanel searchBarAndBtn;
    private JPanel searchResult;
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
        initDetailElement();  // initialize elements in the detail panel.
        selected.setVisible(false); // hide detail panel when startup.
        searchResult = resultList.createPanel(DB.getCourse(null, semesterSelect.getSelectedItem().toString(),isMustSelect.getSelectedIndex()));
        // initialize all cardLayouts.
        tableDisplay = new courseTable().getPanel();
        selected.setBorder(new LineBorder(Color.GRAY, 3));
        resultHolder.add(searchResult,"r");
        resultHolder.add(notFoundPanel,"n");
        layout = (CardLayout)resultHolder.getLayout();
        tableHolder.add(tableDisplay,"t");
        tableLayout = (CardLayout)tableHolder.getLayout();
        layout.show(resultHolder,"r");
        tableLayout.show(tableHolder,"t");
        // btn listeners.
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
                    // check if the choosedcourse array is empty.
                    JOptionPane.showMessageDialog(null,"請先選擇至少一門課程再使用此功能","你沒有必帶嗎",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try{
                        // use auto choose course to automatically fill in courses.
                        Processor.autoChooseCourse(semesterSelect.getSelectedItem().toString());
                        refreshTable(courseStats);
                    }catch(NullPointerException ne){
                        JOptionPane.showMessageDialog(null,"請先選擇至少一門課程再使用此功能","你沒有必帶嗎",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // search field hint text handler.
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                searchField.setText(searchTemp);
                searchField.setForeground(new Color(8, 37, 42));
            }
        });
        // search field hint text handler.
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
        // keyboard enter listener for search trigger.
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if(keyCode == 10){  //Enter button
                    // handle debug mode switch.
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
                        // if field text is not debug command, then search.
                        search();
                    }
                }
            }
        });

        // check if semester is changed, if changed then show a warning and clear result and table.
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
                    selected.setVisible(false);
                }
                else{
                    semesterSelect.setSelectedIndex(currentSemesterIndex);
                }
            }
        });
    }

    // method of the search function.
    public void search(){
        if(searchField.getText().equals("輸入關鍵字 或 直接搜尋")){
            searchCourse = null;
        }
        else{
            searchCourse=searchField.getText();
        }
        Debugger.showDebugMessage("Search for:" + searchCourse);
        ArrayList<CourseData> CD = DB.getCourse(searchCourse,semesterSelect.getSelectedItem().toString(),isMustSelect.getSelectedIndex());
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

    // main function, the start of the GUI application.
    public static void main(String[] args) {
        Debugger.setDebugMode(false);
        try {
            UIManager.setLookAndFeel(metalUI);  // launch using Metal UI mode
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new home().panel1);
        frame.pack();
        frame.getContentPane().requestFocusInWindow();
        frame.setSize(1024, 900); // set initial frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        windowSizeLimiter(frame, 1000, 900);
    }

    // method of refresh course table by new data.
    // use cardLayout to resolve the refresh problem.
    public void refreshTable(ArrayList<ArrayList<CourseData>> data){
        tableHolder.remove(tableDisplay);
        tableDisplay = null;
        tableDisplay = new courseTable(data).getPanel();
        tableHolder.add(tableDisplay,"t");
        tableLayout = (CardLayout)tableHolder.getLayout();
        tableLayout.show(tableHolder,"t");
    }
    // method of refresh search result panel by new data.
    // use cardLayout to resolve the refresh problem.
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

    // make sure all the text in detail panel is empty during startup.
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

    // limit the window size in order to make the app runs correctly.
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

    // intellij custom create element function.
    private void createUIComponents() {
    }

    // inner class renderer.
    // method used to render the custom defined JList (using custom cell elements).
    class JListCustomRenderer extends JFrame {
        private ArrayList<CourseData> displayData = new ArrayList<CourseData>();

        public JListCustomRenderer() {
        }

        // create the panel then return.
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

            // the mouse click listener.
            MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent mouseEvent) {
                    JList<String> theList = (JList) mouseEvent.getSource();
                    if (mouseEvent.getClickCount() == 1) { // Single clicked.
                        int index = theList.locationToIndex(mouseEvent.getPoint());
                        if (index >= 0) {
                            Object o = theList.getModel().getElementAt(index);
                            Debugger.showDebugMessage("Single-clicked on: " + o);
                            // refresh detail panel if single clicked on the element.
                            for (int i = 0; i < displayData.size(); i++) {
                                if (displayData.get(i).getRandom_num().equals(o.toString())) {
                                    detailData = displayData.get(i);
                                    refreshDetailPanel();
                                    break;
                                }
                            }
                        }
                    }
                    if (mouseEvent.getClickCount() == 2) { // Double clicked.
                        int index = theList.locationToIndex(mouseEvent.getPoint());
                        if (index >= 0) {
                            Object o = theList.getModel().getElementAt(index);
                            Debugger.showDebugMessage("Double-clicked on: " + o);
                            // add or remove  the course to array when double clicked on the element.
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
                            Debugger.showDebugMessage("Choosed course: "+choosedCourse);
                            // determine if the course needs to be added or removed.
                            if(isExist){
                                choosedCourse.remove(target);
                            }
                            else{
                                // check if there's too much (>2) course at the same time block.
                                if(Processor.courseAddCheck(courseStats,detailData.getTime())){
                                    choosedCourse.add(detailData);
                                }
                                else {
                                    JOptionPane.showMessageDialog(null,"新增課程與已選課程衝堂! \n 最多只允許衝堂兩堂","無法新增課程",JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            Debugger.showDebugMessage("Choosed course: "+choosedCourse);
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

        // create the JList.
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
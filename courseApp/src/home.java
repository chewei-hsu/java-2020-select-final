import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
=======
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
>>>>>>> 2e28fcd1a8b417df9dcc46a9ca44a2753ef2b5aa

public class home {
    private JPanel searchBarAndBtn;
    private JPanel searchResult;
    private JPanel selected;
    private JPanel panel1;
<<<<<<< HEAD
    private JTextArea textArea1;
    private JButton btnLucky;
    private JButton btnSearch;
    public static String metalUI = "javax.swing.plaf.metal.MetalLookAndFeel";

    public home() {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
            }
        });
=======
    private JTextArea searchField;
    private JButton btnLucky;
    private JButton btnSearch;
    private JPanel btnHolder;
    private String searchTemp = "";
    public static String metalUI = "javax.swing.plaf.metal.MetalLookAndFeel";

    public home() {
        selected.setBorder(new LineBorder(Color.GRAY, 3));
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

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
                        searchField.setText("輸入課程關鍵字");
                        searchTemp = "";
                        searchField.setForeground(new Color(175, 175, 175));
                    } else {
                        searchTemp = searchField.getText();
                    }
                }
            });
>>>>>>> 2e28fcd1a8b417df9dcc46a9ca44a2753ef2b5aa
    }

    public static void main(String[] args) {
        Debugger.setDebugMode(true);
        try {
            UIManager.setLookAndFeel(home.metalUI); // 使用Metal UI 模式啟動
            UIManager.setLookAndFeel(metalUI); // 使用Metal UI 模式啟動
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Course"); // 設定視窗標題
        windowSizeLimiter(frame, 800, 800);
        frame.setContentPane(new home().panel1);
        frame.pack();
        frame.getContentPane().requestFocusInWindow();
        frame.setSize(1000, 800); // 設定初始視窗大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void windowSizeLimiter(JFrame frame, int i, int i1) {
    }

    private void createUIComponents() {
        searchResult = new JListCustomRenderer().createPanel(DB.getCourse(null,"108-1"));
    }

}


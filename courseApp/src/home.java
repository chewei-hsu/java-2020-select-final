import javax.swing.*;
import java.awt.*;

public class home {
    private JPanel searchBarAndBtn;
    private JPanel searchResult;
    private JPanel selected;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton button1;
    private JButton button2;
    public static String metalUI = "javax.swing.plaf.metal.MetalLookAndFeel";

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


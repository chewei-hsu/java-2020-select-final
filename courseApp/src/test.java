import javax.swing.*;

public class test {
    private JPanel panel1;
    private JPanel test1;

    public static void main(String[] args) {
        Debugger.setDebugMode(true);
        try {
            UIManager.setLookAndFeel(home.metalUI); // 使用Metal UI 模式啟動
            UIManager.setLookAndFeel(home.metalUI); // 使用Metal UI 模式啟動
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Course"); // 設定視窗標題
        frame.setContentPane(new test().panel1);
        frame.pack();
        frame.getContentPane().requestFocusInWindow();
        frame.setSize(1000, 800); // 設定初始視窗大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        for(int i = 1 ; i < 6 ; i++){
            for(int j = 1 ; j < 15 ; j++){
                System.out.println("text"+i+j+".set");
            }
        }
    }
    private void createUIComponents() {
        test1 = new courseTable().getPanel();
    }
}

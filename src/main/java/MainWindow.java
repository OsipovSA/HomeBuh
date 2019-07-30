import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergey on 23.07.2019.
 */
public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
        MyThree myThree = new MyThree();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(myThree,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane,BorderLayout.WEST);
        setContentPane(panel);

        // Вывод окна на экран
        setTitle("HomeBuh");

        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

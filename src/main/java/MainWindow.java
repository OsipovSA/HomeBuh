import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergey on 23.07.2019.
 */
public class MainWindow extends JFrame {
    MyThree myThree;
    JButton addButton;
    JButton deleteButton;
    JTextField categoryName;

    public MainWindow() throws HeadlessException {
        myThree = new MyThree();

        // Сразу создадим панели
        JSplitPane splitPane = new JSplitPane();
        JPanel treePanel = new JPanel();
        JPanel tablePanel = new JPanel();

        treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.Y_AXIS));

        JScrollPane jTreeScrollPane = new JScrollPane(myThree,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        treePanel.add(jTreeScrollPane);

        treePanel.add(new JLabel("Введите название:"));

        categoryName = new JTextField();
        treePanel.add(categoryName);

        addButton = new JButton("Аdd category");
        treePanel.add(addButton);

        deleteButton = new JButton("Delete category");
        treePanel.add(deleteButton);

        tablePanel.add(new JLabel("Тут будет таблица"));

        // В главной панели установим горизонтальный лэйаут
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                treePanel, tablePanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        Container contentPane = getContentPane();
        contentPane.add(splitPane,BorderLayout.CENTER);

        // Вывод окна на экран
        setTitle("HomeBuh");

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

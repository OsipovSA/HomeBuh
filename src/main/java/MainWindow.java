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
        JSplitPane splitPane;
        JPanel treePanel = new JPanel();
        JPanel tablePanel = new JPanel();

        treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.Y_AXIS));

        JScrollPane jTreeScrollPane = new JScrollPane(myThree,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        treePanel.add(jTreeScrollPane);
        JLabel categoryLabel = new JLabel("Введите название категории:");
        categoryLabel.setAlignmentX(JComponent.RIGHT_ALIGNMENT);

        treePanel.add(categoryLabel);

        categoryName = new JTextField();
        treePanel.add(categoryName);

        JPanel buttonTreePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        addButton = new JButton("Аdd category");
        buttonTreePanel.add(addButton);

        deleteButton = new JButton("Delete category");
        buttonTreePanel.add(deleteButton);

        treePanel.add(buttonTreePanel);

        //tablePanel.setLayout(new BorderLayout());
        //tablePanel.add(new JLabel("Тут будет таблица"),BorderLayout.NORTH);
        tablePanel.add(new JLabel("Тут будет таблица"));

        // В главной панели установим горизонтальный лэйаут
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                treePanel, tablePanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);

        Container contentPane = getContentPane();
        contentPane.add(splitPane,BorderLayout.CENTER);

        // Вывод окна на экран
        setTitle("HomeBuh");
        setSize(800,600);
        setLocationRelativeTo(null);
        //pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

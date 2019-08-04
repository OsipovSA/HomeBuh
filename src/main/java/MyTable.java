import javax.swing.*;

/**
 * Created by Sergey on 04.08.2019.
 */
public class MyTable extends JTable {
    public MyTable() {
        Records records = Records.getRecords();
        MyTableModel tableModel = new MyTableModel(records);
        this.setModel(tableModel.getTableModel());
    }
}

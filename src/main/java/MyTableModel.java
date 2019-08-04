
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 04.08.2019.
 */
public class MyTableModel extends AbstractTableModel {
 Records records;
    Map<String,List<BuhRecord>> tableRecords;

    public MyTableModel(Records records) {
        this.records = records;
        this.tableRecords = records.getTableRecords();
    }

    @Override
    public int getRowCount() {
        return tableRecords.get(records.getCategory()).size();
    }

    @Override
    public int getColumnCount() {
        return tableRecords.get(records.getCategory()).get(0).getColCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List categoryList = tableRecords.get(records.getCategory());
        BuhRecord buhRecord = (BuhRecord) categoryList.get(rowIndex);
        switch (columnIndex){
            case(0):
                return buhRecord.getCategory();
            case(1):
                return buhRecord.getDate();
            case(2):
                return buhRecord.getName();
            case(3):
                return buhRecord.getSum();
        }
        return "Не определена";
    }
}

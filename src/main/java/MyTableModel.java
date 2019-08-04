
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
    public String getColumnName(int column) {
        switch (column){
            case(0):
                return "Категория";
            case(1):
                return "Дата";
            case(2):
                return "Наименование расхода";
            case(3):
                return "Сумма";
        }
        return "";
    }

    @Override
    public int getRowCount() {
        String category = records.getCategory();
        if(category.equals("Категории")){
            return 0;
        }else {
            return tableRecords.get(category).size();
        }
    }

    @Override
    public int getColumnCount() {
        String category = records.getCategory();
        if(category.equals("Категории")){
            return 0;
        }else {
            return tableRecords.get(category).get(0).getColCount();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String category = records.getCategory();
        if(category.equals("Категории")){
            return null;
        }else{
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
            return "Нет значения";
        }
    }
}

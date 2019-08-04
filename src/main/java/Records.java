import org.w3c.dom.Node;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sergey on 04.08.2019.
 */
// Получается что данный класс должен быть Синглтон
public class Records {

    private static Records instance;
    private HashMap<String, List<BuhRecord>> tableRecords = new HashMap<>();
    private String category;
    Node root = null;

    private Records() {
        // Получаем FileData и из него формируем tableRecords
        root = FileData.getInstance().getRoot();
        // Пока сделаем заглушку чтобы посмотреть что получается
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        BuhRecord buhRecord1 = new BuhRecord("Авто", df.format(new Date()),"Антифриз зеленый",1400.00);
        BuhRecord buhRecord2 = new BuhRecord("Авто", df.format(new Date()),"Масло в двигатель",2100.00);
        BuhRecord buhRecord3 = new BuhRecord("Авто", df.format(new Date()),"Замена антифриза",400.00);
        BuhRecord buhRecord4 = new BuhRecord("Авто", df.format(new Date()),"Замена масла в двигателе",600.00);
        List<BuhRecord> buhRecordList = new ArrayList<>();
        buhRecordList.add(buhRecord1);
        buhRecordList.add(buhRecord2);
        buhRecordList.add(buhRecord3);
        buhRecordList.add(buhRecord4);
        tableRecords.put("Авто",buhRecordList);
        category = "Авто";
    }

    public static Records getInstance() {
        if(instance==null){
            instance = new Records();
        }
        return instance;
    }

    public Map<String, List<BuhRecord>> getTableRecords() {
        return tableRecords;
    }

    public String getCategory() {
        return category;
    }
}

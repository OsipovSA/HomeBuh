import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 04.08.2019.
 */
// Получается что данный класс должен быть Синглтон
public class Records {

    private static Records instance;
    private HashMap<String, List<BuhRecord>> tableRecords = new HashMap<>();
    private String category;

    private Records() {
    }

    public static Records getRecords() {
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

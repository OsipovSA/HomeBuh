import java.util.Date;

/**
 * Created by Sergey on 29.07.2019.
 */
public class BuhRecord {

    private String category;
    private Date date;
    private String name;
    private Double sum;

    public BuhRecord(String category, Date date, String name, Double sum) {
        this.category = category;
        this.date = date;
        this.name = name;
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "BuhRecord{" +
                "category='" + category + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuhRecord buhRecord = (BuhRecord) o;

        if (!category.equals(buhRecord.category)) return false;
        if (!date.equals(buhRecord.date)) return false;
        if (!name.equals(buhRecord.name)) return false;
        if (!sum.equals(buhRecord.sum)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + sum.hashCode();
        return result;
    }
}

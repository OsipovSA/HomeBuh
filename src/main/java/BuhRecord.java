import java.util.Date;

/**
 * Created by Sergey on 29.07.2019.
 */
public class BuhRecord {

    private Date date;
    private String name;
    private Double sum;

    public BuhRecord(Date date, String name, Double sum) {
        this.date = date;
        this.name = name;
        this.sum = sum;
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
                "date=" + date +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuhRecord buhRecord = (BuhRecord) o;

        if (!date.equals(buhRecord.date)) return false;
        if (!name.equals(buhRecord.name)) return false;
        if (!sum.equals(buhRecord.sum)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + sum.hashCode();
        return result;
    }
}

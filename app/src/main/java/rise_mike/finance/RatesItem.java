package rise_mike.finance;

/**
 * Created by Mike on 01.01.2018.
 */

public class RatesItem {
    private String name;
    private Double rate;
    private String fullName;
    private int flag;
    private boolean isFavourite;

    public RatesItem(String name, Double rate, String fullName, int flag) {
        this.name = name;
        this.rate = rate;
        this.fullName = fullName;
        this.flag = flag;
        this.isFavourite = false;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public String getFullName() {
        return fullName;
    }

    public int getFlag() {
        return flag;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}

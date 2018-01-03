package rise_mike.finance;

/**
 * Created by Mike on 01.01.2018.
 */

public class RatesItem {
    private String name;
    private String rate;
    private String primaryCurrency;
    private boolean isFavourite;

    public RatesItem(String name, String rate, String primaryCurrency, boolean isFavourite) {
        this.name = name;
        this.rate = rate;
        this.primaryCurrency = primaryCurrency;
        this.isFavourite = isFavourite;
    }

    public String getName() {
        return name;
    }

    public String getRate() {
        return rate;
    }

    public String getPrimaryCurrency() {
        return primaryCurrency;
    }

    public boolean isFavourite() {
        return isFavourite;
    }
}

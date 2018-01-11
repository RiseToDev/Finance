package rise_mike.finance;

/**
 * Created by Rise on 1/9/2018.
 */

public class CurrencyInformation {
    private String currencyAbbreviation;
    private String currencyFullName;
    private String currencyIcon;
    private String currencyRate;

    public CurrencyInformation(String currencyAbbreviation, String currencyFullName, String currencyIcon) {
        this.currencyAbbreviation = currencyAbbreviation;
        this.currencyFullName = currencyFullName;
        this.currencyIcon = currencyIcon;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public String getCurrencyFullName() {
        return currencyFullName;
    }

    public String getCurrencyIcon() {
        return currencyIcon;
    }

    public void setCurrencyRate(String currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getCurrencyRate() {
        return currencyRate;
    }


}

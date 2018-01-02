package rise_mike.finance;

/**
 * Created by Rise on 1/2/2018.
 *
 * for CurrencyRates and CurrencyEcchanges usage, like a currency object
 *
 */

public class CurrencyRate {
    private String name = "";
    private Double value = 0.0;
    private Boolean isFavorite = Boolean.FALSE;

    public String getName(){
        return this.name;
    }

    public Double getValue(){
        return this.value;
    }

    public Boolean getIsFavorite(){
        return this.isFavorite;
    }

    public CurrencyRate(String name, Double value, Boolean isFavorite){
        this.name = name;
        this.value = value;
        this.isFavorite = isFavorite;
    }
}

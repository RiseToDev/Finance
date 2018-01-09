package rise_mike.finance;

/**
 * Created by Rise on 1/9/2018.
 *
 * Class for currency API usage;
 * Contains API Link and Key
 *
 */

public final class CurrencyAPI {
    private String LINK = "https://v3.exchangerate-api.com/bulk/";
    private String KEY = "eea141b9e02d415609d257a1/";


    public String getAPILink(String toCurrency){
        return this.LINK + this.KEY + toCurrency;
    }

}

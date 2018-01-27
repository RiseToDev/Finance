package rise_mike.finance.rates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import rise_mike.finance.CurrencyInformation;
import rise_mike.finance.R;

/**
 * Created by Mike on 01.01.2018.
 * This is custom adapter for ListView that fill it with particular items from our class
 * CurrencyInformation
 */

public class RatesAdapter extends BaseAdapter {

    private Context location;
    private List<CurrencyInformation> fullRatesInformation; //contents full information of the currency

    /**
     * pattern "ViewHolder" for the optimize usage of device's resources
     */
    private static class ViewHolder {
        TextView currencyAbbreviation;
        TextView currencyFullName;
        ImageView currencyIcon;
        TextView currencyRate;
        // ImageButton isFavourite_button;
    }

    public RatesAdapter(Context location, List<CurrencyInformation> fullRatesInformation) {
        this.location = location;
        this.fullRatesInformation = fullRatesInformation;
    }

    @Override
    public int getCount() {
        return fullRatesInformation.size();
    }

    @Override
    public Object getItem(int position) {
        return fullRatesInformation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Fill the each line of ListView by the data
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CurrencyInformation ratesItem = (CurrencyInformation) getItem(position);
        ViewHolder viewHolder;
        DecimalFormat round = new DecimalFormat(".##");
        if (convertView == null) {
            LayoutInflater customListView = (LayoutInflater)
                    location.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = customListView.inflate(R.layout.rates_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.currencyIcon = convertView.findViewById(R.id.currency_flag);
            viewHolder.currencyAbbreviation = convertView.findViewById(R.id.currency_abbreviation);
            viewHolder.currencyFullName = convertView.findViewById(R.id.currency_full_name);
            viewHolder.currencyRate = convertView.findViewById(R.id.currency_rate);
            // viewHolder.isFavourite_button = convertView.findViewById(R.id.favourite_button);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.currencyIcon.setText(ratesItem.getCurrencyAbbreviation());
        viewHolder.currencyAbbreviation.setText(ratesItem.getCurrencyAbbreviation());
        viewHolder.currencyFullName.setText(ratesItem.getCurrencyFullName());

        Double rate = Math.round(Double.parseDouble(ratesItem.getCurrencyRate()) * 100.0) / 100.0;
        viewHolder.currencyRate.setText(rate.toString());

        /*viewHolder.isFavourite_button.setOnClickListener(view -> {
            ratesItem.setFavourite(!ratesItem.isFavourite());
        });*/
        return convertView;
    }


}
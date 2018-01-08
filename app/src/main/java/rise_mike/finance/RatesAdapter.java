package rise_mike.finance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mike on 01.01.2018.
 */

public class RatesAdapter extends BaseAdapter {

    private Context context;
    private List<RatesItem> ratesList;
    private String primaryCurrency;

    private static class ViewHolder {
        TextView currencyName;
        TextView currencyRate;
        TextView currencyFullName;
        ImageView currencyFlag;
        TextView toPrimaryCurrency;
        ImageView favouriteButton;
    }

    public RatesAdapter(Context context, List<RatesItem> ratesList, String primaryCurrency) {
        this.context = context;
        this.ratesList = ratesList;
        this.primaryCurrency = primaryCurrency;
    }

    @Override
    public int getCount() {
        return ratesList.size();
    }

    @Override
    public Object getItem(int position) {
        return ratesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RatesItem ratesItem = (RatesItem) getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rates_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.currencyName = convertView.findViewById(R.id.currency_name);
            viewHolder.currencyRate = convertView.findViewById(R.id.currency_rate);
            viewHolder.currencyFullName = convertView.findViewById(R.id.currency_full_name);
            viewHolder.currencyFlag = convertView.findViewById(R.id.currency_flag);
            viewHolder.toPrimaryCurrency = convertView.findViewById(R.id.to_primary_currency);
            viewHolder.favouriteButton = convertView.findViewById(R.id.favourite_button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.currencyName.setText(ratesItem.getName());
        viewHolder.currencyRate.setText(String.valueOf(ratesItem.getRate()));
        viewHolder.currencyFullName.setText(ratesItem.getFullName());
        viewHolder.currencyFlag.setImageResource(ratesItem.getFlag());
        viewHolder.toPrimaryCurrency.setText(String.valueOf("to 1 " + primaryCurrency));
        switchButton(ratesItem, viewHolder);
        viewHolder.favouriteButton.setOnClickListener(view -> {
            ratesItem.setFavourite(!ratesItem.isFavourite());
            switchButton(ratesItem, viewHolder);
        });

        return convertView;
    }

    private void switchButton(RatesItem ratesItem, ViewHolder viewHolder) {
        if (!ratesItem.isFavourite()) {
            viewHolder.favouriteButton.setImageResource(R.drawable.ic_fav_button_off);
        } else {
            viewHolder.favouriteButton.setImageResource(R.drawable.ic_fav_button_on);
        }
    }
}

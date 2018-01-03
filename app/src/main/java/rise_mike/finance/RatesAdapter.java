package rise_mike.finance;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

/**
 * Created by Mike on 01.01.2018.
 */

public class RatesAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<RatesItem> ratesList;

    public RatesAdapter(Context context, List<RatesItem> ratesList) {
        this.ratesList = ratesList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.rates_list_item, parent, false);
            view.setOnClickListener(null);
        }

        RatesItem ratesItem = (RatesItem) getItem(position);

        ((TextView) view.findViewById(R.id.currency_name)).setText(ratesItem.getName());
        ((TextView) view.findViewById(R.id.currency_rate)).setText(ratesItem.getRate());
        ((TextView) view.findViewById(R.id.to_primary_currency)).setText(ratesItem.getPrimaryCurrency());

        return view;
    }
}

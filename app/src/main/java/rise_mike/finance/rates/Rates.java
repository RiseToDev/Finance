package rise_mike.finance.rates;

import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rise_mike.finance.CurrencyInformation;
import rise_mike.finance.DataCollaboration;
import rise_mike.finance.R;

public class Rates extends AppCompatActivity {

    private ListView ratesListView;
    private DataCollaboration data;
    private String sortType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rates);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Spinner sortSpinner = findViewById(R.id.sort_spinner);

        String[] sortSpinnerArray = {"Alphabet", "Favourites first", "Popularity"};

        ArrayAdapter<String> sortSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sortSpinnerArray);
        sortSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortSpinnerAdapter);*/

        ratesListView = findViewById(R.id.listView);
        data = new DataCollaboration(this);
        data.getCurrencyInformation().getRatesData(ratesListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rates_toolbar_menu, menu);
        sortType = "Alphabet sort";
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_button) {
            if (item.isChecked()) {
                item.setIcon(R.drawable.ic_action_sort_reverse);
                item.setChecked(false);
                if (sortType.equals("Alphabet sort")) {
                    data.reverseInfoArray().getRatesData(ratesListView);
                }
            } else {
                item.setIcon(R.drawable.ic_action_sort);
                item.setChecked(true);
                if (sortType.equals("Alphabet sort")) {
                    data.reverseInfoArray().getRatesData(ratesListView);
                }
            }
            Toast.makeText(this, "sort_button clicked", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.alphabet_sort) {
            sortType = "Alphabet sort";
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            Toast.makeText(this, "alphabet_sort clicked", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.favourites_sort) {
            sortType = "Favourites sort";
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            Toast.makeText(this, "favourites_sort clicked", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.popularity_sort) {
            sortType = "Popularity sort";
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            Toast.makeText(this, "popularity_sort clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
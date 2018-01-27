package rise_mike.finance.rates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.sql.Array;
import java.util.ArrayList;

import rise_mike.finance.CurrencyInformation;
import rise_mike.finance.DataCollaboration;
import rise_mike.finance.R;

public class Rates extends AppCompatActivity {
    private ListView ratesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rates);
        Toolbar toolbar = findViewById(R.id.toolbar_rates);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ratesListView = findViewById(R.id.listView);

        new DataCollaboration(this).getRatesData(ratesListView);

        String[] sortSpinnerArray = new String[]{
                "Alphabet",
                "Favourites first",
                "Popularity"
        };

        Spinner spinner = findViewById(R.id.sort_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.rates_sort_spinner_item, sortSpinnerArray);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.rates_sort_spinner_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

}
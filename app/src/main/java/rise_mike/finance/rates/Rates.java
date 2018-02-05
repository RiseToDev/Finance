package rise_mike.finance.rates;

import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import rise_mike.finance.DataCollaboration;
import rise_mike.finance.R;

public class Rates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rates);

        Toolbar toolbar = findViewById(R.id.toolbar_rates);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner sortSpinner = findViewById(R.id.sort_spinner);

        String[] sortSpinnerArray = {"Alphabet", "Favourites first", "Popularity"};

        ArrayAdapter<String> sortSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sortSpinnerArray);
        sortSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortSpinnerAdapter);

        ListView ratesListView = findViewById(R.id.listView);
        new DataCollaboration<ListView>(this).getRatesData(ratesListView);
    }
}
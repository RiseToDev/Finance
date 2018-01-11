package rise_mike.finance.rates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Adapter;
import android.widget.ListView;

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


    }

}
package rise_mike.finance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Rates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rates);

        RequestQueue queue = Volley.newRequestQueue(this);

        Toolbar toolbar = findViewById(R.id.toolbar_rates);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView ratesList = findViewById(R.id.listView);
        queue.add(getRatesData(ratesList, "USD"));

        //ratesList.add(new RatesItem("USD", "United States Dollar", R.drawable.usa, "27.00"));
        //ratesList.add(new RatesItem("USD", "United States Dollar", R.drawable.usa, "27.00"));


    }

    public StringRequest getRatesData(ListView ratesListView, String currency) {

        List<RatesItem> ratesList = new ArrayList<>();
        RatesAdapter ratesAdapter = new RatesAdapter(this, ratesList);
        String url = "https://v3.exchangerate-api.com/bulk/eea141b9e02d415609d257a1/";

        return new StringRequest(Request.Method.GET,
                url + currency.toUpperCase(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String rates = object.getString("rates");
                            rates = rates.replaceAll("[\"{}]", "");
                            String[] ratesArray = rates.split(",");
                            for (String elem : ratesArray) {
                                String[] currencyAndRate = elem.split(":");
                                if (!currencyAndRate[0].equals(currency)) {
                                    ratesList.add(new RatesItem(currencyAndRate[0], currencyAndRate[1], "to 1 " + currency, false));
                                }
                            }
                            ratesListView.setAdapter(ratesAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Rates.this,
                        "Something goes wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
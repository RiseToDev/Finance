package rise_mike.finance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rates);

        RequestQueue queue = Volley.newRequestQueue(this);

        Toolbar toolbar = findViewById(R.id.toolbar_rates);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView ratesListView = findViewById(R.id.listView);
        queue.add(getRatesData(ratesListView, "USD"));
    }

    public StringRequest getRatesData(ListView ratesListView, String currency) {
        List<RatesItem> ratesList = new ArrayList<>();
        RatesAdapter ratesAdapter = new RatesAdapter(this, ratesList, currency);
        Map<String, String> fullNameMap = setCurrencyExtraInfo(R.raw.currency_full_name_list);
        Map<String, String> flagMap = setCurrencyExtraInfo(R.raw.currency_flag_list);
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
                                String[] str = elem.split(":");
                                if (!str[0].equals(currency)) {
                                    ratesList.add(new RatesItem(str[0], Double.parseDouble(str[1]),
                                            fullNameMap.get(str[0]),
                                            getResources().getIdentifier(flagMap.get(str[0]),
                                                    "drawable", getPackageName())));
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

    public Map<String, String> setCurrencyExtraInfo(int resource) {
        Map<String, String> map = new HashMap<>();
        String line;
        InputStream inputStream = getResources().openRawResource(resource);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split(":");
                map.put(str[0], str[1]);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
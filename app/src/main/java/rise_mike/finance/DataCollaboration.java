package rise_mike.finance;


import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rise_mike.finance.rates.RatesAdapter;


/**
 * Created by Rise on 1/9/2018.
 * Class for using different methods and functions for collaboration with data on the device
 */

public final class DataCollaboration<T> {
    private Context location;
    private ArrayList<CurrencyInformation> infoArray = new ArrayList<>();

    public DataCollaboration(Context location) {
        this.location = location;
    }

    public ArrayList<CurrencyInformation> getInfoArray() {
        return this.infoArray;
    }

    /**
     * Read data (for example: UAH:Ukrainian hryvnia:flag_ukraine) and fill the ArrayList.
     * Return ArrayList.
     */
    public void getCurrencyInformation() {
        String txtLine;
        InputStream StreamReader = location.getResources().openRawResource(R.raw.currency_information);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(StreamReader));
        try {
            while ((txtLine = bufferedReader.readLine()) != null) {
                String[] strSplit = txtLine.split(":");
                this.infoArray.add(new CurrencyInformation(strSplit[0], strSplit[1], strSplit[2]));
                //Abbreviation, Full name, Icon
            }
            StreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get data from API Link.
     * Return String for future editing and parsing.
     */
    public void getRatesData(T buf) {
        if (buf instanceof ListView) {
            ListView ratesListView = (ListView) buf;
            getCurrencyInformation();
            RequestQueue getJSONRequest = Volley.newRequestQueue(location);
            getJSONRequest.add(new StringRequest(Request.Method.GET, new CurrencyAPI().getRatesLink("USD"),
                    response -> {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            for (CurrencyInformation i : infoArray) {
                                i.setCurrencyRate(jObject.getJSONObject("rates").getString(i.getCurrencyAbbreviation()));
                            }
                            ratesListView.setAdapter(new RatesAdapter(location, infoArray));
                        } catch (JSONException e) {
                            Toast.makeText(location, "Error on server side!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> Toast.makeText(location, "System error!", Toast.LENGTH_LONG).show()));
        } else /*if ()*/ {

            //


        }
    }
}



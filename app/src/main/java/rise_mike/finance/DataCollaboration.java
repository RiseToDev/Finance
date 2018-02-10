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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rise_mike.finance.rates.RatesAdapter;


/**
 * Created by Rise on 1/9/2018.
 * Class for using different methods and functions for collaboration with data on the device
 */

public final class DataCollaboration {
    private Context location;
    private List<CurrencyInformation> infoArray;

    public DataCollaboration(Context location) {
        this.location = location;
    }

    public List<CurrencyInformation> getInfoArray() {
        return infoArray;
    }

    /**
     * Read data (for example: UAH:Ukrainian hryvnia:flag_ukraine) and fill the ArrayList.
     * Return ArrayList.
     */
    public DataCollaboration getCurrencyInformation() {
        infoArray = new ArrayList<>();
        String txtLine;
        InputStream StreamReader = location.getResources().openRawResource(R.raw.currency_information);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(StreamReader));
        try {
            while ((txtLine = bufferedReader.readLine()) != null) {
                String[] strSplit = txtLine.split(":");
                infoArray.add(new CurrencyInformation(strSplit[0], strSplit[1], strSplit[2]));
                //Abbreviation, Full name, Icon
            }
            StreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Get data from API Link.
     * Return String for future editing and parsing.
     */
    public void getRatesData(Object buf) {
        if (buf instanceof ListView) {
            ListView ratesListView = (ListView) buf;
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

    public DataCollaboration reverseInfoArray() {
        Collections.reverse(infoArray);
        return this;
    }

    /**
     * To round the values of the rate of each currency
     */
    public String getRound(String value) {
        if (value.contains(".")) {
            String[] numberSplit = value.split("\\.");
            Double buf = Double.parseDouble(numberSplit[1]);
            if (buf == 0) {
                return numberSplit[0];
            } else {
                buf = Double.parseDouble(value);
                DecimalFormat df = new DecimalFormat("#.###");
                buf = Double.valueOf(df.format(buf));
                return buf.toString();
            }
        } else return value;
    }
}



package rise_mike.finance;


import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Rise on 1/9/2018.
 * Class for using different methods and functions for collaboration with data on the device
 */

public final class DataCollaboration extends AsyncTask<Void, Void, Void> {
    private Context location;
    private String buf;
    private ArrayList<CurrencyInformation> infoArray = new ArrayList<>();
    private ArrayList<String> ratesArray = new ArrayList<>();

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
    /*public void getCurrencyInformation() {
        String txtLine;
        InputStream StreamReader = location.getResources().openRawResource(R.raw.currency_information);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(StreamReader));
        try {
            while ((txtLine = bufferedReader.readLine()) != null) {
                String[] strSplit = txtLine.split(":");
                this.infoArray.add(new CurrencyInformation(strSplit[0], strSplit[1], strSplit[2]));
                //Abbreviation, Fullname, Icon
            }
            StreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Get data from API Link.
     * Return String for future editing and parsing.
     */
    private void getRatesData(String toCurrency) {
        String txtLine;
        InputStream StreamReader = location.getResources().openRawResource(R.raw.currency_information);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(StreamReader));
        try {
            while ((txtLine = bufferedReader.readLine()) != null) {
                String[] strSplit = txtLine.split(":");
                this.infoArray.add(new CurrencyInformation(strSplit[0], strSplit[1], strSplit[2], ""));
                //Abbreviation, Fullname, Icon
            }
            StreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Setting URL
            String url_str = new CurrencyAPI().getAPILink(toCurrency);
            // Making Request
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            // Accessing object
            for (String i: this.ratesArray)
            {

                    i = jsonobj.get("rates").getAsJsonObject().get("AED").getAsString();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected Void doInBackground(Void... voids) {

        this.getRatesData("USD");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        for (CurrencyInformation i: this.infoArray) {
            i.setCurrencyRate(ratesArray.get(0));
        }


    }
}



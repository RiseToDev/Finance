package rise_mike.finance;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Content extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView currencyListView;
    private String url = "https://v3.exchangerate-api.com/bulk/eea141b9e02d415609d257a1/USD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(view -> {
            currencyListView = findViewById(R.id.currencyListView);
            StringRequest request = new StringRequest(url,
                    this::parseJsonData_Currency,
                    volleyError -> Toast.makeText(this,
                            "Something goes wrong!", Toast.LENGTH_SHORT).show());
            RequestQueue rQueue = Volley.newRequestQueue(this);
            rQueue.add(request);
        });*/

        /*---------------------------------------------------------Spinner - currencyList-----------------------------------------*/


    }


    ArrayList<String> listOfCurrencies = new ArrayList<>();

    private void getListOfCurrencies() {
        try (BufferedReader br = new BufferedReader(new FileReader("listOfCurrencies.txt"))) {
            String currency;
            while ((currency = br.readLine()) != null) {
                listOfCurrencies.add(currency);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setListOfCurrencies() {
        try {
            FileOutputStream writeCurrencies = openFileOutput("listOfCurrencies.txt", MODE_PRIVATE);
            for (String o : listOfCurrenciesForFile) {
                writeCurrencies.write(o.getBytes());
            }
            writeCurrencies.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ArrayList<String> listOfCurrenciesForFile = new ArrayList<>();

    /*---------------------------------------------------------Spinner - currencyList - END------------------------------------*/
   /* private void parseJsonData_Currency(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            String str = object.getString("rates");
            List<HashMap<String, String>> currencyList = new ArrayList<>();
            HashMap<String, String> map;

            String[] values = str.split(",");
            for (String o : values) {
                o = o.replaceAll("[\"{}]", "");
                String[] currency_value = o.trim().split(":");
                map = new HashMap<>();
                map.put("Currency", currency_value[0]);
                listOfCurrenciesForFile.add(currency_value[0]);
                map.put("Value", currency_value[1]);
                currencyList.add(map);
            }

            ArrayAdapter<String> adapterForSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfCurrenciesForFile);
            adapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner currencyListSpinner = findViewById(R.id.currencyList);
            currencyListSpinner.setAdapter(adapterForSpinner);
            currencyListSpinner.setPrompt("Set currency");


            currencyListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    currencyListSpinner.setSelection(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            setListOfCurrencies();
            getListOfCurrencies();
            SimpleAdapter adapter = new SimpleAdapter(this, currencyList, android.R.layout.simple_list_item_2,
                    new String[]{"Currency", "Value"},
                    new int[]{android.R.id.text1, android.R.id.text2});
            currencyListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

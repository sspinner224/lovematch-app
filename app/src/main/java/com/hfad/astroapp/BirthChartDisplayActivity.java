package com.hfad.astroapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.util.Log;
import androidx.appcompat.widget.ShareActionProvider;
import android.widget.Spinner;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;
import androidx.core.view.MenuItemCompat;


import android.view.Menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.net.HttpURLConnection;


public class BirthChartDisplayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ShareActionProvider shareActionProvider;
    private String[] items;

    YourSignHandler yourSignHandler = new YourSignHandler();
    OtherSignHandler otherSignHandler = new OtherSignHandler();

    boolean userSelect = false;

    private String url1 = "https://devbrewer-horoscope.p.rapidapi.com/match/";
    private String LOG_TAG = BirthChartDisplayActivity.class.getSimpleName();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_chart_display);

        items = new String[2];
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //YOUR SIGN SPINNER
        Spinner yourSignSpinner = (Spinner)findViewById(R.id.your_sign_spinner);
        ArrayAdapter<String> yourSignAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yourSignHandler.signs);
        yourSignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yourSignSpinner.setAdapter(yourSignAdapter);
        yourSignSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (userSelect) {
                    final String item = (String) parent.getItemAtPosition(position);
                    Log.i("onItemSelected :year", item);

                    items[0] = item;
                    //TODO : call of async subclass goes here
                  //  new FetchBirthInfo().execute(item);

                    userSelect = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //OTHER SIGN SPINNER
        Spinner otherSignSpinner = (Spinner)findViewById(R.id.other_sign_spinner);
        ArrayAdapter<String> otherSignAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, otherSignHandler.signs);
        otherSignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        otherSignSpinner.setAdapter(otherSignAdapter);
        otherSignSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (userSelect) {
                    final String item = (String) parent.getItemAtPosition(position);
                    Log.i("onItemSelected :sign2", item);
                    items[1] = item;

                    //TODO : call of async subclass goes here
                 //   new FetchBirthInfo().execute(item);

                    userSelect = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Want to find your love match?");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_create_order:
//                Intent intent = new Intent(this, ResultActivity.class);
//                startActivity(intent);
//                return true;
//            default:
             return super.onOptionsItemSelected(item);
//        }
    }

    //FOR SHARE BUTTON
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

   // @Override
    public void onSelect(View view) {
        new FetchBirthInfo().execute();
        Log.e("button pressed", "button has been pressed");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userSelect = true;

    }

    //PRIVATE ASYNC TASK CLASS TO RETRIEVE LOVE MATCH DATA
    class FetchBirthInfo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String birthInfo = null;
            try {

                URL url = new URL(url1 + items[0] + "/" + items[1]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-RapidAPI-Key", "2997c190cemsh0a78cfe4c7adbfep1fc105jsnf42c6c41bf87");
                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                if(in == null)
                 return null;

                    reader = new BufferedReader(new InputStreamReader(in));
                    birthInfo = getStringFromBuffer(reader);


            } catch (Exception e) {
                Log.e(LOG_TAG, "Error" + e.getMessage());
                return null;
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Error" + e.getMessage());
                        return null;
                    }
                }

            }
             return birthInfo;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result != null) {
                Log.d(LOG_TAG, "Result is null");
                Intent intent = new Intent(BirthChartDisplayActivity.this, ResultActivity.class);
                intent.putExtra("birthInfo", result);
                startActivity(intent);
            }
        }
    }

    private String getStringFromBuffer(BufferedReader bufferedReader) {

        StringBuffer buffer = new StringBuffer();
        String line;

        if(bufferedReader != null) {
            try {
                while((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + '\n');
                }
                    bufferedReader.close();
                    return yourSignHandler.getBirthInfo(buffer.toString());
                } catch (Exception e) {
                    Log.e("BirthChartDisplayAct", "Error" + e.getMessage());
                    return null;
                } finally  {


            }
        }
        return null;
    }


}
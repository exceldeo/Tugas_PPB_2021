package com.example.tugas_1_ppb_perhitungan_abc.contactApi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugas_1_ppb_perhitungan_abc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactApiActivity extends AppCompatActivity {
    private ListView lvCountry;
    ArrayList<HashMap<String, String>> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_api);
        getSupportActionBar().setTitle("List Country App");

        countryList = new ArrayList<>();
        lvCountry = findViewById(R.id.lvCountry);
        new GetContact().execute();
    }

    private class GetContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            HTTPHandler sh = new HTTPHandler();
            String url = "https://covid19.mathdro.id/api/countries";
            String jsonStr = sh.makeServiceCall(url);
            Log.e("JSONParser", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // getting JSON array node
//                    JSONArray contactsdata = jsonObj.getJSONArray("data");
                    JSONArray countries = jsonObj.getJSONArray("countries");
                    // looping through all contacts
                    for (int i = 0; i < countries.length(); i++) {
                        JSONObject c = countries.getJSONObject(i);

                        String name = c.getString("name");
                        String iso2 = c.getString("iso2");
                        String iso3 = c.getString("iso3");
                        Log.i("JSONParser", name + " " + iso2 + " " + iso3);
                        // temporary hash map for single contact
                        HashMap<String, String> countrie = new HashMap<>();
                        // adding each child node to HashMap key => value
                        countrie.put("name", name);
                        countrie.put("iso2", iso2);
                        countrie.put("iso3", iso3);
                        // adding contact to contactList
                        countryList.add(countrie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ContactApiActivity.this, "JSON Data is being downloaded", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(ContactApiActivity.this, countryList, R.layout.country,
                    new String[] {"name", "iso2", "iso3"},
                    new int[] {R.id.tvName, R.id.tvIso2,R.id.tvIso3});
            lvCountry.setAdapter(adapter);
        }
    }
}
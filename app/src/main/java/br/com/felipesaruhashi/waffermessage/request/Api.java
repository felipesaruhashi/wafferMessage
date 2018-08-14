package br.com.felipesaruhashi.waffermessage.request;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import br.com.felipesaruhashi.waffermessage.models.Country;
import br.com.felipesaruhashi.waffermessage.models.Language;

public class Api {

    public static String BASE_URL = "https://restcountries.eu/rest/v2/all";

    private ArrayList<Country> countries = null;
    private Runnable runnable;


    public void getAll(Runnable runnable) {
        new RequestGet().execute("");
        this.runnable = runnable;
    }

    public List<Country> getCountries() {
        return countries;
    }

    private class RequestGet extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {

                URL myUrl = new URL(BASE_URL);

                HttpURLConnection conn = (HttpURLConnection) myUrl
                        .openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.connect();

                int statusCode = conn.getResponseCode();
                if (statusCode != 200){
                    return null;
                }

                InputStream inputStream = conn.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                String response = buffer.toString();


                return response;

//                JSONArray ja = new JSONArray(response);
            } catch (Exception e) {
                return "";
            }


        }

        @Override
        protected void onPostExecute(String s) {


            countries = new ArrayList<Country>();



            try {
                JSONArray ja = new JSONArray(s);

                for( int i =0; i< ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);

                    Country l = new Country(jo);

                    countries.add(l);



                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            runnable.run();

        }
    }


}

package br.com.felipesaruhashi.waffermessage.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Country {


    public String name;
    public List<Currency> currencies;
    public List<Language> languages;


    public Country() {

    }

    public Country(JSONObject jo ) {

        currencies = new ArrayList<>();
        languages = new ArrayList<>();

        try {
            this.name = jo.getString("name");

            JSONArray jaLanguages = jo.getJSONArray("languages");
            for (int i=0; i< jaLanguages.length(); i++ ) {
                JSONObject languageJsonObject = jaLanguages.getJSONObject(i);
                Language language = new Language(languageJsonObject);
                languages.add(language);
            }



            JSONArray jaCurrencies = jo.getJSONArray("currencies");
            for (int i=0; i< jaCurrencies.length(); i++ ) {
                JSONObject currencyJsonObject = jaCurrencies.getJSONObject(i);
                Currency currency = new Currency(currencyJsonObject);
                currencies.add(currency);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

package br.com.felipesaruhashi.waffermessage.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Language {

    public String iso639_1;
    public String iso639_2;
    public String name;
    public String nativeName;


    public Language() {

    }

    public Language(JSONObject jo ) {
        try {
            this.iso639_1 = jo.getString("iso639_1");
            this.iso639_2 = jo.getString("iso639_2");
            this.name = jo.getString("name");
            this.nativeName = jo.getString("nativeName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

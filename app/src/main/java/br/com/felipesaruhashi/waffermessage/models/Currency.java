package br.com.felipesaruhashi.waffermessage.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Currency {

    public String code;
    public String name;
    public String symbol;

    public Currency(JSONObject jo) {
        try {
            this.code = jo.getString("code");
            this.name = jo.getString("name");
            this.symbol = jo.getString("symbol");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

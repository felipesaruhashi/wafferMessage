package br.com.felipesaruhashi.waffermessage.ui;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.felipesaruhashi.waffermessage.R;
import br.com.felipesaruhashi.waffermessage.models.Country;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {


    private List<Country> countries;

    public Adapter(List<Country> coutries) {
        this.countries = coutries;
    }

    public Adapter() {

    }

    public void setCountries(List<Country> coutries) {
        this.countries = coutries;
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.country_item, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Country country = countries.get(position);


        holder.tvName.setText(country.name);

        if ( country.currencies.size() > 0) {
            holder.tvCurrencyName.setText(country.currencies.get(0).name);
        }

        if ( country.languages.size() > 0) {
            holder.tvLanguageName.setText(country.languages.get(0).name);
        }

    }

    @Override
    public int getItemCount() {

        if ( this.countries == null ) return 0;
        else return this.countries.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);

            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvCurrencyName = itemView.findViewById(R.id.tvCurrencyName);
            this.tvLanguageName = itemView.findViewById(R.id.tvLanguageName);
        }

        public TextView tvName;
        public TextView tvCurrencyName;
        public TextView tvLanguageName;


    }


}

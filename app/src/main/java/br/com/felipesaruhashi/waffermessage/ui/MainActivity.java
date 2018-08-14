package br.com.felipesaruhashi.waffermessage.ui;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.felipesaruhashi.waffermessage.R;
import br.com.felipesaruhashi.waffermessage.models.Country;
import br.com.felipesaruhashi.waffermessage.request.Api;

public class MainActivity extends AppCompatActivity {

    private Api api = new Api();

    private Adapter adapter;

    private RecyclerView rvCountries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvCountries = findViewById(R.id.rvCountries);


        adapter = new Adapter();

        rvCountries.setAdapter(adapter);
        rvCountries.setLayoutManager( new LinearLayoutManager(this));
        rvCountries.setItemAnimator( new DefaultItemAnimator());


        ActionButtons actionButtons = new ActionButtons() {
            @Override
            public void onDeleteButon(int position) {
                List<Country> countries = adapter.getCountries();

                countries.remove(position);

                adapter.notifyDataSetChanged();


            }
        };

        final ItemTouchHelper.Callback callback =
                new ItemTouchCallBack(actionButtons, this);
        final ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvCountries);

        rvCountries.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                ((ItemTouchCallBack )callback).onDraw(c);
            }
        });


        api.getAll(new Runnable() {
            @Override
            public void run() {
                adapter.setCountries(api.getCountries());
                adapter.notifyDataSetChanged();
            }
        });
    }
}

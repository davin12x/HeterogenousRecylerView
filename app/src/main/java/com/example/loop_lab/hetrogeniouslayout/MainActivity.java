package com.example.loop_lab.hetrogeniouslayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView.LayoutManager mLayoutManager;
    private HashMap<Integer,String> cardHolders;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private  ComplexAdapter adapter;
    HashMap<String,ArrayList<HashMap<Integer,Integer>>> second = new HashMap<>();
    ArrayList<HashMap<Integer, Integer>> cardCollection = new ArrayList<>();
    ArrayList<HashMap<String,ArrayList<HashMap<Integer, Integer>>>> hashMaps = new ArrayList<>();
    HashMap<Integer, Integer> card_key = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ArrayList<Integer> arrayList = new ArrayList<>();
        setSupportActionBar(toolbar);
        cardHolders = new HashMap<>();
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ComplexAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.addNewView(1);
                convertToJson(System.currentTimeMillis(), 1);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addNewView(0);
                convertToJson(System.currentTimeMillis(), 0);

            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                System.out.println(direction);
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return super.isItemViewSwipeEnabled();
            }


        };

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        read();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void readValue() {
        int size = sharedPref.getInt("keycount",100);
        for(int j=0; j < size; j++) {

            if (sharedPref.getInt("key",100) != 100) {
                adapter.addNewView(sharedPref.getInt("key",100));
                System.out.println(sharedPref.getInt("key",100));
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void convertToJson(long time, int cardType) {

        card_key.put((int)time, cardType);

        cardCollection.add(card_key);



        second.put("Home", cardCollection);

        //Top Level
        hashMaps.add(second);
        final Gson gson = new Gson();
        String text =  gson.toJson(hashMaps);

        System.out.println(text);

        editor.putString("Home", text);
        editor.commit();

    }

    private void read() {
        String lanSettings = sharedPref.getString("Sweet", null);
        System.out.println(lanSettings);
    }


}

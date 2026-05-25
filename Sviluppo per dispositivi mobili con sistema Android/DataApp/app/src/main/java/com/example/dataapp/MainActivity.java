package com.example.dataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String JASON_TEST = "JASON_TEST";

    private ListView personeListView;

    private ArrayList<Persona> personeArrayList;
    private PersonaAdapter adapter;

    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personeListView = findViewById(R.id.personeListView);

        // Initialize list ALWAYS
        personeArrayList = new ArrayList<>();

        // Restore data if exists
        if (savedInstanceState != null) {
            ArrayList<Persona> savedList =
                    savedInstanceState.getParcelableArrayList("personeKey");

            if (savedList != null) {
                personeArrayList.addAll(savedList);
            }
        }

        // Create adapter ONCE
        adapter = new PersonaAdapter(this,
                R.layout.rowcustom,
                personeArrayList);

        personeListView.setAdapter(adapter);

        // Click listener
        personeListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(getBaseContext(), SecondActivity.class);
            intent.putExtra("persona", personeArrayList.get(position));
            startActivity(intent);
        });

        // Long click listener
        personeListView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Log.d("ONITEMLONGCLICK", "ID: " + id);
            return true;
        });

        // Executor init ONCE
        executor = Executors.newSingleThreadExecutor();

        // Load data ONLY if list is empty
        if (personeArrayList.isEmpty()) {
            loadData();
        }
    }

    private void loadData() {
        executor.execute(() -> {

            HttpHandler sh = new HttpHandler();
            String url = "https://innovazionetop.com/test_data/persone_2.json";

            String jsonStr = sh.makeServiceCall(url);

            Log.e(JASON_TEST, "Response: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray persone = jsonObj.getJSONArray("persone");

                    ArrayList<Persona> tempList = new ArrayList<>();

                    for (int i = 0; i < persone.length(); i++) {
                        JSONObject e = persone.getJSONObject(i);

                        String id = e.getString("id");
                        String nome = e.getString("nome");
                        String cognome = e.getString("cognome");
                        String anni = e.getString("anni");

                        tempList.add(new Persona(id, nome, cognome, anni));
                    }

                    runOnUiThread(() -> {
                        personeArrayList.clear();
                        personeArrayList.addAll(tempList);

                        adapter.notifyDataSetChanged();
                    });

                } catch (JSONException e) {
                    Log.e(JASON_TEST, "JSON error: " + e.getMessage());
                }
            } else {
                Log.e(JASON_TEST, "No data from server");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("personeKey", personeArrayList);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (executor != null) {
            executor.shutdown();
        }
    }
}
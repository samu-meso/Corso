package com.example.dataapp;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowInsets;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LIST_POSITION = "list_position";

    private ListView personeListView;

    private ArrayList<String> nomi;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // 🔥 THIS is the key line
        //WindowCompat.setDecorFitsSystemWindows(getWindow(), true);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        personeListView = findViewById(R.id.personeListView);

        // Initialize list
        nomi = new ArrayList<>();

        // Add data manually (NO JSON)
        nomi.add("Luca");
        nomi.add("Marco");
        nomi.add("Giulia");
        nomi.add("Anna");
        nomi.add("Paolo");
        nomi.add("Luca");
        nomi.add("Marco");
        nomi.add("Giulia");
        nomi.add("Anna");
        nomi.add("Paolo");
        nomi.add("Luca");
        nomi.add("Marco");
        nomi.add("Giulia");
        nomi.add("Anna");
        nomi.add("Paolo");
        nomi.add("Luca");
        nomi.add("Marco");
        nomi.add("Giulia");
        nomi.add("Anna");
        nomi.add("Paolo");
        nomi.add("Luca");
        nomi.add("Marco");
        nomi.add("Giulia");
        nomi.add("Anna");
        nomi.add("Paolo");
        nomi.add("Luca");
        nomi.add("Marco");
        nomi.add("Giulia");
        nomi.add("Anna");
        nomi.add("Paolo");

        // Create adapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nomi
        );

        personeListView.setAdapter(adapter);

        // Click listener
        personeListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            Log.d("ONITEMCLICK", nomi.get(position));
            Log.d("ONITEMCLICK",String.valueOf(position));

            intent.putExtra("nome", nomi.get(position));
            intent.putExtra(LIST_POSITION, position);

            startActivity(intent);
        });
    }
}
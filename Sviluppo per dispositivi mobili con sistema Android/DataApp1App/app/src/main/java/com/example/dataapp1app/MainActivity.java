package com.example.dataapp1app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView personeListView;
    private ArrayList<String> nomi;
    private ArrayAdapter<String> adapter;
    public static final String LIST_POSITION = "LIST_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        personeListView= findViewById(R.id.personeListView);

        nomi = new ArrayList<>();

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

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                nomi
        );

        personeListView.setAdapter(adapter);


        personeListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            Log.d("ONITEMCLICK", nomi.get(position));
            Log.d("ONITEMCLICK",String.valueOf(position));

            intent.putExtra("nome", nomi.get(position));
            intent.putExtra("posizione", position);

            startActivity(intent);
        });
    }
}
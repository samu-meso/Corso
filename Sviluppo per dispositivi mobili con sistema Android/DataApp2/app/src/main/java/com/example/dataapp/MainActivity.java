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
import android.view.View;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LIST_POSITION = "list_position";

    private ListView personeListView;

    private ArrayList<Persona> personeArrayList;

    private ArrayAdapter<Persona> adapter;

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

        //2.
        personeArrayList = new ArrayList<>();
        personeArrayList.add(new Persona("0", "Gabriele", "Garavelli", "25"));
        personeArrayList.add(new Persona("1", "Daniele", "Nocito", "46"));
        personeArrayList.add(new Persona("2", "Feifei", "Ye", "32"));
        personeArrayList.add(new Persona("3", "Giovanni", "Agosti", "24"));
        personeArrayList.add(new Persona("4", "Dario", "Arcangeli", "21"));
        personeArrayList.add(new Persona("5", "Matteo", "Filomena", "22"));
        personeArrayList.add(new Persona("6", "Lorenzo", "Fontana", "21"));
        personeArrayList.add(new Persona("7", "Daniel", "Moussine", "27"));
        personeArrayList.add(new Persona("8", "Marianna", "Toscanesi", "20"));
        personeArrayList.add(new Persona("9", "Alessia", "Gulminelli", "19"));
        personeArrayList.add(new Persona("10", "Giacomo", "Melli", "23"));
        personeArrayList.add(new Persona("11", "Fabio", "Cerlini", "21"));
        personeArrayList.add(new Persona("12", "Samuel", "Bighi", "27"));
        personeArrayList.add(new Persona("13", "Mouad", "Ettalibi", "21"));
        personeArrayList.add(new Persona("14", "Leonardo", "Acerbi", "20"));
        personeArrayList.add(new Persona("15", "Simone", "Samaroli", "19"));
        personeArrayList.add(new Persona("16", "Ciccio", "Caio", "67"));

        adapter = new ArrayAdapter<Persona>(
                this,
                android.R.layout.simple_list_item_1,
                personeArrayList);

        personeListView.setAdapter(adapter);

        // Click listener
        /*personeListView.setOnItemClickListener((adapterView, view, position, id) -> {

            Persona persona = personeArrayList.get(position);

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            Log.d("ONITEMCLICK", persona.getNome());
            Log.d("ONITEMCLICK", String.valueOf(position));

            // Option 1 (recommended): pass the whole object
            intent.putExtra("persona", persona);

            // Option 2 (optional): also pass position
            intent.putExtra(LIST_POSITION, position);

            startActivity(intent);
        });*/

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long id) {

                Persona persona = (Persona) adapterView.getItemAtPosition(position);

                if (persona == null) return;

                Log.d("ONITEMCLICK", "position: " + position);
                Log.d("ONITEMCLICK", persona.toString());

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("persona", persona);

                startActivity(intent);
            }
        };

        personeListView.setOnItemClickListener(clickListener);
    }
}
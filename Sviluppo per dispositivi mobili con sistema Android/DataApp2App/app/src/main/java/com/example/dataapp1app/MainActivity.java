package com.example.dataapp1app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private ArrayAdapter<String> adapter;

    private ArrayList<Persona> personeArrayList;
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

        personeListView = findViewById(R.id.personeListView);

        personeArrayList = new ArrayList<Persona>();
        personeArrayList.add(new Persona("1234", "Luca", "Bonacorsi", "51"));
        personeArrayList.add(new Persona("1031", "Damiano", "Iotti", "21"));
        personeArrayList.add(new Persona("2222", "Constantin", "Chicioroaga", "27"));
        personeArrayList.add(new Persona("8756", "Lorenzo", "Badia", "37"));
        personeArrayList.add(new Persona("1889", "Adolf", "Hitler", "56"));
        personeArrayList.add(new Persona("4567", "Samuele", "Mesoraca", "21"));
        personeArrayList.add(new Persona("6666", "Thomas", "Croci", "51"));
        personeArrayList.add(new Persona("9999", "Cristian", "Giorgi", "29"));
        personeArrayList.add(new Persona("6767", "Cristian", "Caleffi", "20"));
        personeArrayList.add(new Persona("1337", "Daniel", "Mendes Rodrigues", "69"));
        personeArrayList.add(new Persona("6100", "Matteo", "Martinelli", "34"));
        personeArrayList.add(new Persona("9876", "Simona", "Capodivento", "420"));
        personeArrayList.add(new Persona ("2401", "Anna", "Ruini", "25"));
        personeArrayList.add(new Persona("4682", "Gian Marco", "Di Mingo", "35"));
        personeArrayList.add(new Persona("6969", "TRAVIS", "SCOTT", "99"));
        personeArrayList.add(new Persona("6990", "Gabriele", "Gualtieri", "26"));
        personeArrayList.add(new Persona("5342", "Lorenzo", "Di Costanzo", "19"));
        personeArrayList.add(new Persona("1251", "Oubeidllah", "Guirat", "25"));

        personeListView.setAdapter(adapter);

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
                intent.putExtra("posizione", position);

                startActivity(intent);
            }
        };
    }
}
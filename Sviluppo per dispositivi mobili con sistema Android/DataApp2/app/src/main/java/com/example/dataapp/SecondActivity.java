package com.example.dataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Persona persona = intent.getParcelableExtra("persona");

        int position = intent.getIntExtra(MainActivity.LIST_POSITION, -1);

        // Views
        TextView personaTextView = findViewById(R.id.personaTextView);
        TextView listPositionTextView = findViewById(R.id.listPositionTextView);
        Button backButton = findViewById(R.id.backButton);

        if (persona != null) {
            String text = persona.getNome() + " " +
                    persona.getCognome() + " (" +
                    persona.getAnni() + " anni)";
            personaTextView.setText(text);
        } else {
            personaTextView.setText("No data");
        }

        listPositionTextView.setText("Position: " + position);

        // Back button
        backButton.setOnClickListener(v -> finish());
    }
}
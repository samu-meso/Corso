package com.example.dataapp1app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        // Get data from MainActivity
        //String nome = intent.getStringExtra("nome");
        Persona persona = intent.getParcelableExtra("persona");

        int position = intent.getIntExtra("posizione", -1);

        // Views
        TextView personaTextView = findViewById(R.id.personaTextView);
        TextView listPositionTextView = findViewById(R.id.listPositionTextView);
        Button backButton = findViewById(R.id.backButton);

        // Set data
        //personaTextView.setText(nome);

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
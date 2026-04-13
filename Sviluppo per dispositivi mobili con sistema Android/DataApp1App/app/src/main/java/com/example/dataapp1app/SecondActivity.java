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
        String nome = intent.getStringExtra("nome");
        int position = intent.getIntExtra("posizione", -1);

        TextView personaTextView = findViewById(R.id.personaTextView);
        TextView listPositionTextView = findViewById(R.id.listPositionTextView);
        Button backButton = findViewById(R.id.backButton);

        personaTextView.setText(nome);
        listPositionTextView.setText("Posizione: " + position);

        backButton.setOnClickListener(v -> finish());
    }
}
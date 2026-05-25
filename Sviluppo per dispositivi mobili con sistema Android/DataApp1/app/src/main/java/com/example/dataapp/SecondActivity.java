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

        // Get data from MainActivity
        String nome = intent.getStringExtra("nome");
        int position = intent.getIntExtra(MainActivity.LIST_POSITION, -1);

        // Views
        TextView personaTextView = findViewById(R.id.personaTextView);
        TextView listPositionTextView = findViewById(R.id.listPositionTextView);
        Button backButton = findViewById(R.id.backButton);

        // Set data
        personaTextView.setText(nome);
        listPositionTextView.setText("Position: " + position);

        // Back button
        backButton.setOnClickListener(v -> finish());
    }
}
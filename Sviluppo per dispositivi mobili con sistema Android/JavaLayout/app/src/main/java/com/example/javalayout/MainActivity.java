package com.example.javalayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout ciccio;
    private int idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ciccio = findViewById(R.id.ciccio);
        configureLayout();
    }

    private void configureLayout() {
        Button myButton = new Button(this);
        myButton.setText("PRESS ME");
        myButton.setBackgroundColor(0xFF00FF00);
        myButton.setId(View.generateViewId());

        EditText myEditText = new EditText(this);
        idEditText = View.generateViewId();
        myEditText.setId(idEditText);

        ciccio.addView(myButton);
        ciccio.addView(myEditText);
    }
}
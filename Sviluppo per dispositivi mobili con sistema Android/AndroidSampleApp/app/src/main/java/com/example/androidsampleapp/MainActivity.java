package com.example.androidsampleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);*/
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
    public void convertCurrency(View view) {
        EditText dollarText = findViewById(R.id.dollarText);
        TextView euroText = findViewById(R.id.euroText);

        if(!dollarText.getText().toString().isEmpty())
        {
            float dollarValue = Float.parseFloat(dollarText.getText().toString());
            float euroValue = dollarValue*0.86F;
            euroText.setText(String.valueOf(euroValue));
        }
        else
        {
//            euroText.setText("Non convertibile.");
            euroText.setText(R.string.no_value_string);
        }
    }
}
package com.example.verifica;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_CHANGE = "STATE_CHANGE";

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


        Button buttonSecond = findViewById(R.id.buttonSecond);
        Button buttonThird = findViewById(R.id.buttonThird);

        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });
        buttonThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdActivity();
            }
        });
    }


    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        String firstImage = "Questa immagine rappresenta il logo dei RHCP";
        intent.putExtra("image1_desc", firstImage);
        startActivity(intent);
    }
    public void openThirdActivity() {
        Intent intent = new Intent(this, ThirdActivity.class);
        String secondImage = "Questa immagine rappresenta il chitarrista dei RHCP";
        intent.putExtra("image2_desc", secondImage);
        startActivity(intent);
    }

}
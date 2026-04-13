package com.example.twoactiv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private static String TEST_VALUE = "TEST_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Button backButton = findViewById(R.id.backButton);
        TextView extrasTextView = findViewById(R.id.extrasTextView);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            String specie = extras.getString("specie");
            String razza = extras.getString("razza");

            Log.i(TEST_VALUE, specie);
            Log.i(TEST_VALUE, razza);

            extrasTextView.setText(specie + " " + razza);
        }
    }

    public void back() {
        Intent intent = new Intent();
        intent.putExtra("id", 3);
        intent.putExtra("totale", 503);
        setResult(RESULT_OK, intent);

        finish();
    }
}
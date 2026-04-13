package com.example.twoactiv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private static String TEST_VALUE = "TEST_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
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
            String name = extras.getString("name");
            int anni = extras.getInt("anni");

            Log.i(TEST_VALUE, name);
            Log.i(TEST_VALUE, String.valueOf(anni));

            extrasTextView.setText(name + " " + anni);
        }
    }

    public void back() {
        Intent intent = new Intent();
        intent.putExtra("id", 2);
        intent.putExtra("saluto", "Ciao!");
        setResult(RESULT_OK, intent);

        finish();
    }
}
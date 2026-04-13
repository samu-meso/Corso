package com.example.twoactiv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final String TEST_VALUE = "TEST_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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
            int age = extras.getInt("age");

            Log.i(TEST_VALUE, name);
            System.out.println(age);
            Log.i(TEST_VALUE, String.valueOf(age));

            extrasTextView.setText(name + " " + age);
        }
    }

    public void back() {
        Intent intent = new Intent();
        intent.putExtra("saluto", "Ciao!");
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

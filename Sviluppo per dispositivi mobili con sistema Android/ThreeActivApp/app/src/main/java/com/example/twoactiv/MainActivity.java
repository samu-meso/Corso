package com.example.twoactiv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.twoactiv.SecondActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    private static String TEST_VALUE = "TEST_VALUe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openButton = findViewById(R.id.openButton);

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });
        Button openButton2 = findViewById(R.id.openButton2);

        openButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdActivity();
            }
        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        Bundle extras = intent.getExtras();
                        if (extras!= null)
                        {
                            String saluto = extras.getString("saluto");
                            Log.i(TEST_VALUE, saluto);

                            TextView salutoTextView = findViewById(R.id.salutoTextView);
                            salutoTextView.setText(saluto);
                        }
                    }
                }
        );
    }

    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra("name", "Samuele");
        intent.putExtra("age", 21);

        launcher.launch(intent);
        //startActivity(intent);
    }
    public void openThirdActivity() {
        Intent intent = new Intent(this, ThirdActivity.class);


        launcher.launch(intent);
        //startActivity(intent);
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

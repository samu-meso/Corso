package com.example.twoactiv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static String TEST_VALUE = "TEST_VALUE";

    private ActivityResultLauncher<Intent> launcher;
    //private ActivityResultLauncher<Intent> launcher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Button openButton = findViewById(R.id.openButton);
        Button openButton2 = findViewById(R.id.openButton2);
        Button openButton3 = findViewById(R.id.openButton3);

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });

        openButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdActivity();
            }
        });

        openButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage();
            }
        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        Bundle extras = intent.getExtras();

                        if(extras!=null) {
                            int id = extras.getInt("id");

                            if (id == 2) {
                                String saluto = extras.getString("saluto");

                                Log.i(TEST_VALUE, saluto);

                                TextView salutoTextView = findViewById(R.id.salutoTextView);

                                salutoTextView.setText(saluto);
                            } else if (id == 3) {
                                int totale = extras.getInt("totale");

                                Log.i(TEST_VALUE, String.valueOf(totale));

                                TextView totaleTextView = findViewById(R.id.totaleTextView);

                                totaleTextView.setText(String.valueOf(totale));
                            }

                            /*if (extras.containsKey("saluto")) {
                                String saluto = extras.getString("saluto");

                                Log.i(TEST_VALUE, saluto);

                                TextView salutoTextView = findViewById(R.id.salutoTextView);

                                salutoTextView.setText(saluto);
                            } else if (extras.containsKey("totale")) {
                                int totale = extras.getInt("totale");

                                Log.i(TEST_VALUE, String.valueOf(totale));

                                TextView totaleTextView = findViewById(R.id.totaleTextView);

                                totaleTextView.setText(String.valueOf(totale));
                            }*/
                        }
                    }
                }
        );

        /*launcher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        Bundle extras = intent.getExtras();

                        if(extras!=null) {
                            int totale = extras.getInt("totale");

                            Log.i(TEST_VALUE, String.valueOf(totale));

                            TextView totaleTextView = findViewById(R.id.totaleTextView);

                            totaleTextView.setText(String.valueOf(totale));
                        }
                    }
                }
        );*/
    }

    public void openSecondActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("name", "Mario");
        intent.putExtra("anni", 44);
        //startActivityForResult(intent, 1);

        launcher.launch(intent);
    }

    public void openThirdActivity() {
        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
        intent.putExtra("specie", "gatto");
        intent.putExtra("razza", "british short-hair");
        //startActivityForResult(intent, 1);

        //launcher2.launch(intent);
        launcher.launch(intent);
    }

    public void openWebPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));

        startActivity(intent);
    }
}
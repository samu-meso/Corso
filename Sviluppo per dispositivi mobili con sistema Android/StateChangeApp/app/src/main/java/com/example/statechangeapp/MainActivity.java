package com.example.statechangeapp;

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

    private static final String STATE_CHANGE = "StateChange";
    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        Log.i(STATE_CHANGE, "onCreate");
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {
            n = savedInstanceState.getInt("n");
            Log.i(STATE_CHANGE, "onCreate -> savedInstanceState -> n: " +n);
        }
        else
        {
            Log.i(STATE_CHANGE, "onCreate -> n " + n);
        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        n = n + 1;
                        Log.i(STATE_CHANGE, "n " + n);
                    }
                }
        );
    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.i(STATE_CHANGE, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(STATE_CHANGE, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(STATE_CHANGE, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(STATE_CHANGE, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(STATE_CHANGE, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(STATE_CHANGE, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        final EditText editText = findViewById(R.id.editText);
        final EditText editText2 = findViewById(R.id.editText2);
        final EditText editText3 = findViewById(R.id.editText3);

        CharSequence userText = editText.getText() + "s";
        CharSequence userText2 = editText2.getText() + "s";
        CharSequence userText3 = editText3.getText() + "s";


        outState.putCharSequence("nome", userText);
        outState.putCharSequence("cognome", userText2);
        outState.putCharSequence("indirizzo", userText3);
        outState.putInt("n", n);

        Log.i(STATE_CHANGE, "onSaveInstanceState");
    }


    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Log.i(STATE_CHANGE, "onRestoreInstanceState");

        final EditText editText = findViewById(R.id.editText);
        final EditText editText2 = findViewById(R.id.editText2);
        final EditText editText3 = findViewById(R.id.editText3);

        CharSequence userText = savedInstanceState.getCharSequence("nome");
        CharSequence userText2 = savedInstanceState.getCharSequence("cognome");
        CharSequence userText3 = savedInstanceState.getCharSequence("indirizzo");

        editText.setText(userText);
        editText2.setText(userText2);
        editText3.setText(userText3);
    }



}
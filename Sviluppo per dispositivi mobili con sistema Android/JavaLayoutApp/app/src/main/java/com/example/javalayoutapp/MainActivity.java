package com.example.javalayoutapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout ciccio;
    private int idEditText;

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

        ciccio = findViewById(R.id.ciccio);

        configureLayout();
    }

    private void configureLayout() {
        Button myButton = new Button(this);
        myButton.setText("PRESS ME");
        myButton.setBackgroundColor(0xFF00FF00);
        //myButton.setId(R.id.myButton);
        myButton.setId(View.generateViewId());

        EditText myEditText = new EditText(this);
        //myEditText.setId(R.id.myEditText);
        idEditText = View.generateViewId();
        myEditText.setId(idEditText);

        //myEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        myEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        myEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        int px = convertToPx(200);
        //myEditText.setWidth(px);
        myEditText.setMinWidth(px);

        ciccio.addView(myButton);
        ciccio.addView(myEditText);

        ConstraintSet set = new ConstraintSet();

        set.constrainHeight(myButton.getId(),
                ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(myButton.getId(),
                ConstraintSet.MATCH_CONSTRAINT);

        set.connect(myButton.getId(), ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(myButton.getId(), ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(myButton.getId(), ConstraintSet.TOP,
                ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(myButton.getId(), ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

        set.constrainHeight(myEditText.getId(),
                ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(myEditText.getId(),
                ConstraintSet.WRAP_CONTENT);

        set.connect(myEditText.getId(), ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(myEditText.getId(), ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(myEditText.getId(), ConstraintSet.BOTTOM,
                myButton.getId(), ConstraintSet.TOP, 70);

        set.applyTo(ciccio);
    }

    private int convertToPx(int value) {
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value,
                r.getDisplayMetrics());
        return px;
    }
}
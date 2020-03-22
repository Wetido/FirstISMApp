package com.example.firstismapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GradesCalculator extends AppCompatActivity {

    TextView gradesCos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_calculator);

        Intent intent = getIntent();
        String grades = intent.getStringExtra("grades");

        gradesCos = (TextView)findViewById(R.id.gradesNumberA2);
        gradesCos.setText(grades);

        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);


        for (int i = 1; i <= Integer.parseInt(grades); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText("button " + id_);
            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            ll.addView(btn, params);

        }

    }

}

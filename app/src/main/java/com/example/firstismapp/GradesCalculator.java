package com.example.firstismapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class GradesCalculator extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_calculator);

        Intent intent = getIntent();
        final String grades = intent.getStringExtra("grades");
        final String name = intent.getStringExtra( "name");
        final String surName = intent.getStringExtra("surName");

        final LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayoutMain);
        final Integer intGrades = Integer.parseInt(grades);

        for (int i = 0; i < intGrades; i++) {

            TextView text = new TextView( GradesCalculator.this );
            text.setId(i + intGrades);
            text.setBackgroundColor(Color.rgb(70, 80, 90));

            SeekBar seekBar = new SeekBar(this);
            seekBar.setId(i);
            seekBar.setMax(6);
            seekBar.setProgress(0);
            seekBar.setBackgroundColor(Color.rgb(65, 75, 85));

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

                    final Integer id = seekBar.getId();
                    final TextView text2 = findViewById(id + intGrades);
                    text2.setText( valueOf(progress) );
                }
            });

            ll.addView(seekBar);
            ll.addView(text);

        }

        Button button = new Button(this);
        button.setText("Oblicz");
        button.setBackgroundColor(Color.rgb(80,200,200));
        ll.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Double avg;
                Integer count = 0;
                Boolean validate = true;

                for (int i = 0; i < Integer.parseInt(grades); i++) {

                    SeekBar seekBar = (SeekBar)findViewById(i);
                    if(seekBar.getProgress() == 0){
                        validate = false;
                        break;
                    }
                    count += seekBar.getProgress();
                }

                if( validate ){

                    avg = count/Double.parseDouble(grades);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result", avg);
                    resultIntent.putExtra("result2", name + " " + surName);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                } else {

                    Toast.makeText(GradesCalculator.this, "WARTOSC NIE MOZE BYC 0", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}

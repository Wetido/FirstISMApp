package com.example.firstismapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    public static final int CALCULATE=3;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[A-Z]{1}+[a-zA-Z0-9]{2,250}");
    private static final Pattern GRADES_PATERN = Pattern.compile("^[0-9]*$");

    Button calculate;
    EditText usName;
    EditText usSurName;
    EditText usGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = (Button)findViewById(R.id.caculateActivityButton);
        usName = (EditText)findViewById(R.id.nameInput);
        usSurName = (EditText)findViewById(R.id.surNameInput);
        usGrades = (EditText)findViewById(R.id.gradesNumberInput);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = usName.getText().toString();
                final String userSurName = usSurName.getText().toString();
                final String userGrades = usGrades.getText().toString();

                if (userName.equals("") || userSurName.equals("") || userGrades.equals("")) {

                    Toast.makeText(MainActivity.this, "DANE NIE MOGA BYC PUSTE",Toast.LENGTH_LONG).show();

                } else if (!CheckUsername(userName) || !CheckUsername(userSurName) || !CheckGrades(userGrades)) {
                    Toast.makeText(MainActivity.this, "PODAJ PRAWIDŁOWE DANE", Toast.LENGTH_LONG).show();
                }else if (Integer.parseInt( userGrades ) > 50){

                    Toast.makeText(MainActivity.this, "MAKSYMALNA LICZBA OCEN - 50", Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(getApplicationContext(), GradesCalculator.class);
                    i.putExtra("grades", userGrades );
                    i.putExtra("name", userName );
                    i.putExtra("surName", userSurName );
                    startActivityForResult(i,1);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                double gradeAVG = data.getDoubleExtra("result", 0);
                String fullName = data.getStringExtra("result2");

                TextView gradeResult = findViewById(R.id.avgGrade);
                gradeResult.setText( "Witaj " + fullName + " Twoja średnia ocen to " + String.valueOf( gradeAVG ) );
            }
        }
    }

    private boolean CheckUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    private boolean CheckGrades(String grades) {
        return GRADES_PATERN.matcher(grades).matches();
    }

}

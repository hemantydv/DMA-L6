package com.Component1.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Component1.quizapplication.R;

public class ParticipantActivity extends AppCompatActivity {

    EditText name;
    Button buttonok ,buttoncancel;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);

        name =(EditText) findViewById(R.id.txt_activity_participant_name);
        buttonok = (Button) findViewById(R.id.btn_activity_participant_ok);
        buttoncancel = (Button) findViewById(R.id.btn_activity_participant_cancel);

        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName()){
                    return;
                }
                else{


                    String nameval = name.getText().toString();
                    Intent intent = new Intent(ParticipantActivity.this,QuizActivity.class);
                    intent.putExtra("User_Name",nameval);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private Boolean validateName(){
        String val = name.getText().toString();

        if(val.isEmpty()){
            name.setError("Field Cannot be Empty");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }
}
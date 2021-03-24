package com.Component1.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Component1.quizapplication.R;

public class ScoreActivity extends AppCompatActivity {

    TextView txt_name ,txt_score;
    Button btn_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        String username = getIntent().getStringExtra("User_Name");
        setTitle(getResources().getString(R.string.app_name)+" :"+username);
        int score = getIntent().getIntExtra("score",0);
        txt_name = (TextView) findViewById(R.id.tv_activity_score_name);
        txt_score = (TextView) findViewById(R.id.tv_activity_score_score);
        btn_finish = (Button) findViewById(R.id.btn_result_activity_close);
        txt_name.setText("Username :  "+username);
        txt_score.setText("Score :  " +Integer.toString(score));
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               System.exit(0);
            }
        });
    }
}
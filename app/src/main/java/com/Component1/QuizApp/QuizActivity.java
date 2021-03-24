package com.Component1.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Component1.quizapplication.R;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    
    private TextView tvQuestion, tvscore, tvtimer,tvquestion;

    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4;
     Button btnNext,btnPrevious;
    private List<QuestionList> questionsList;
    private String user_Name;

    int totalQuestions;
    int counter = 0;
    int score = 0;
    ColorStateList dfcolor;
    boolean answered;


    private QuestionList currentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionsList = new ArrayList<>();

        tvQuestion = findViewById(R.id.txt_quiz_activity_question);

        radioGroup = findViewById(R.id.rg_quiz_activity);

        rb1 = findViewById(R.id.rb_quiz_activity_opt1);
        rb2 = findViewById(R.id.rb_quiz_activity_opt2);
        rb3 = findViewById(R.id.rb_quiz_activity_opt3);
        rb4 = findViewById(R.id.rb_quiz_activity_opt4);
        btnNext = (Button) findViewById(R.id.btn_quiz_activity_next);

        dfcolor = rb1.getTextColors();

        addquestions();

        totalQuestions = questionsList.size();
        showNextQuestion();


        btnNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(answered == false){
                   if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                       checkAnswer();
                   }
                   else{
                       Toast.makeText(QuizActivity.this,"Please select an option",Toast.LENGTH_SHORT).show();
                   }
                }
                else{
                    showNextQuestion();
                }
            }
        });

         user_Name = getIntent().getStringExtra("User_Name");
    }

    private void checkAnswer() {

        answered = true;
        RadioButton rbselected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbselected) + 1;
        if(answerNo == currentQuestion.getCorrect()){
            score++;
        }
        //rb1.setTextColor(Red);

        if(counter < totalQuestions){
            btnNext.setText("Next");
        }
        else{
            btnNext.setText("Finish");
            btnNext.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                    intent.putExtra("score",score);
                    intent.putExtra("User_Name",user_Name);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

    private void showNextQuestion() {

        radioGroup.clearCheck();


        if(counter < totalQuestions){
                currentQuestion = questionsList.get(counter);
                tvQuestion.setText(currentQuestion.getQuestion());
                rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            counter++;
            btnNext.setText("Submit");
            answered = false;
        }
        else{
            finish();
        }
    }

    private void addquestions(){
        questionsList.add(new QuestionList("Which of the following is not a programming language?","Java","PHP","Python","Html",4));
        questionsList.add(new QuestionList("What is the capital city of Nepal ?","Kathmandu","Delhi","London","Trontto",1));
        questionsList.add(new QuestionList("Which of the following is not a RDBMS?" ,"MS,SQL","Oracle","MongoDB","MySql",3));
        questionsList.add(new QuestionList("Which of the following is not an OS?" ,"Windows","Linux","Ubuntu","Word",4));
        questionsList.add(new QuestionList("What is the height of Mount Everest?" ,"8848 metres","8849 metres","8847 metres","8088 metres",1));
        questionsList.add(new QuestionList("A source program is the program written in which level language?" ,"English","Symbolic","High-Level","Machine",3));
        questionsList.add(new QuestionList("Which has the maximum capacity?" ,"Floppy Disk","Zip Disk","Super Disk","Jaz-catridge",4));
        questionsList.add(new QuestionList("The time for which a peace of equipment operation is called?" ,"Seek Time","effective time","access","real time",2));
        questionsList.add(new QuestionList("Which of the following is generally used for back-up?" ,"CD-ROM","Hard disk","Floppy disk","Magnetic tape",4));
        questionsList.add(new QuestionList("Which is a non-standard version of computing language?","PROLOG","APL","ARMY","PL/1",3));
    }


}
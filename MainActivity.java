package com.sachin.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button btn0,btn1,btn2,btn3,btnPlayAgain;
    TextView tvCorrect,tvScore,tvSum,tvTimer;
    RelativeLayout gameRelativeLayout;
    int locationOfCorrectAnswer;
    int numberOfQuestions = 0;
    int score = 0;
    //PLAY AGAIN BUTTON CODE START HERE:
    public void btnPlayAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        tvTimer.setText("30s");
        tvScore.setText("0/0");
        tvCorrect.setText("");
        btnPlayAgain.setVisibility(View.INVISIBLE);
        generateQuestion();

//COUNTDOWNTIMER CODE START HERE :
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(String.valueOf(millisUntilFinished/ 1000)+ "s");

            }

            @Override
            public void onFinish() {

                btnPlayAgain.setVisibility(View.VISIBLE);
                tvTimer.setText("0s");
                tvCorrect.setText("your score " + Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));

            }
        }.start();
    }
// GENERATE RANDOM QUESTION CODE START HERE :
    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        tvSum.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        // answers.clear(); function working to clear the previous answer in the arrayList;
        answers.clear();

        int inCorrectAnswer;
        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {

                inCorrectAnswer = rand.nextInt(41);
                while (inCorrectAnswer == a + b) {
                    inCorrectAnswer = rand.nextInt(41);
                }
                answers.add(inCorrectAnswer);
            }
        }
        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }

//CHOOSE ANSWER BUUTON CODE START HERE :
    public void btnChooseAnswer(View view){

        Log.i("Tag", (String) view.getTag());
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            tvCorrect.setText("correct!");

            Log.i("correct", "correct");

        }else {
            tvCorrect.setText("Wrong!");
        }
        numberOfQuestions++;
        tvScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

// BUTTON BTNsTART ONCLICK CODE START HERE :
    public void Start(View view){
        btnStart.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        btnPlayAgain(findViewById(R.id.btnPlayAgain));
    }
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        btnStart = (Button) findViewById(R.id.btnStart);
        tvCorrect = (TextView) findViewById(R.id.tvCorrect);
        tvScore = (TextView) findViewById(R.id.tvScore);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        tvSum = (TextView) findViewById(R.id.tvSum);
        tvTimer = (TextView)findViewById(R.id.tvTimer);
    }
}

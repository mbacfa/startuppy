package com.example.android.startuppy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts the quiz by moving to the QuestionsActivity
     */
    public void openQuiz(View view) {
        Intent quiz = new Intent(this, QuestionsActivity.class);
        startActivity(quiz);
    }

}

package com.example.android.startuppy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.stream.IntStream;

public class ResultsActivity extends AppCompatActivity {

    int[] results;
    int totalCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        results = getIntent().getIntArrayExtra("RESULTS_ARRAY");
        displayResults();
        Toast.makeText(this, this.getString(R.string.toast), Toast.LENGTH_LONG).show();
    }

    /**
     * shows total number of correct answers and total number of answered questions
     */

    public void displayResults() {
        // Update results score
        TextView userResults = (TextView) findViewById(R.id.user_results);
        totalCorrect = 0;
        for (int element : results) {
            totalCorrect += element;
        }
        //Update total answered count
        userResults.setText(String.valueOf(totalCorrect));
        TextView totalQuestions = (TextView) findViewById(R.id.total_questions);
        totalQuestions.setText(String.valueOf(results.length));
    }


    public void moveToBonus(View view) {
        Intent moveToBonusIntent = new Intent(this, BonusActivity.class);
        moveToBonusIntent.putExtra("TOTAL_CORRECT", String.valueOf(totalCorrect));
        startActivity(moveToBonusIntent);
    }

}

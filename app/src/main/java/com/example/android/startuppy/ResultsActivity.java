package com.example.android.startuppy;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import java.util.stream.IntStream;

public class ResultsActivity extends AppCompatActivity {

    int[] results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        results = getIntent().getIntArrayExtra("RESULTS_ARRAY");
        displayResults();
    }

    /**
     * shows total number of correct answers and total number of answered questions
     */

    public void displayResults () {
        // Update results score
        TextView userResults = (TextView) findViewById(R.id.user_results);
        int totalCorrect = 0;
        for (int element : results) {
            totalCorrect += element;
        }
        //Update total answered count
        userResults.setText(String.valueOf(totalCorrect));
        TextView totalQuestions = (TextView) findViewById(R.id.total_questions);
        totalQuestions.setText(String.valueOf(results.length));
    }

    public void displayResources () {
        Resources res = getResources();
        String resource = res.getString(R.string.learningResources);
        TextView resourceList = (TextView) findViewById(R.id.resource_list);
        resourceList.setText(resource);
    }

}

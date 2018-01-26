package com.example.android.startuppy;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionsActivity extends AppCompatActivity {

    private static final int[] radioButtonIdArray = {R.id.option1, R.id.option2, R.id.option3, R.id.option4};
    int currentQuestion;
    int[] results; //array to store 1 and 0 for correct and incorrect answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        currentQuestion = 0;

        Resources res = getResources();
        int[] answers = res.getIntArray(R.array.answers);
        int count = answers.length;
        results = new int[count];

        updateQuestionAndOptions();
    }


    /**
     * Updates question and answer options based on a current question number
     */

    private void updateQuestionAndOptions () {
        Resources res = getResources();

        String[] questions = res.getStringArray(R.array.questions);
        TextView questionText = (TextView) findViewById(R.id.question_text);
        questionText.setText(questions[currentQuestion]);

        TypedArray optionsReferenceArray = res.obtainTypedArray(R.array.optionsReferenceArray);
        int n = optionsReferenceArray.length();
        String[][] questionOptions = new String[n][];
        for (int i = 0; i < n; i++) {
            int id = optionsReferenceArray.getResourceId(i,0);
            questionOptions[i] = res.getStringArray(id);
        }
        optionsReferenceArray.recycle();

        for (int i = 0; i < radioButtonIdArray.length; i++) {
            RadioButton answerOption = (RadioButton) findViewById(radioButtonIdArray[i]);
            answerOption.setText(questionOptions[currentQuestion][i]);
        }
    }


    /**
     * Replaces Next button with Finish button if a last question is reached
     * Replaces Finish button with Next button if user returns to previous questions
     */

    public void visibilityFinishNext () {
        Resources res = getResources();
        String[] questions = res.getStringArray(R.array.questions);
        Button next = (Button) findViewById(R.id.next_button);
        Button finish = (Button) findViewById(R.id.finish_button);
        if (currentQuestion < questions.length-1) {
            next.setVisibility(View.VISIBLE);
            finish.setVisibility(View.GONE);
        } else {
            next.setVisibility(View.GONE);
            finish.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Updates Questions screen with next question and answer options
     */

    public void moveToNextQuestion (View v) {
        checkCorrectness();
        Resources res = getResources();
        String[] questions = res.getStringArray(R.array.questions);
        if (currentQuestion < questions.length-1) {
            currentQuestion++;
            clearSelection();
            updateQuestionAndOptions();
            visibilityFinishNext();
        }
    }

    /**
     * Updates Questions screen with previous question and answer options
     */

    public void moveToPreviousQuestion (View v) {
        checkCorrectness();
        if (currentQuestion > 0) {
            currentQuestion--;
            clearSelection();
            updateQuestionAndOptions();
            visibilityFinishNext();
        }
    }

    /**
     * compares user radio button selection with the correct answer radio button selection and increments score if match
     */

    public void checkCorrectness () {
        Resources res = getResources();
        int[] answers = res.getIntArray(R.array.answers);
        for (int i = 0; i < radioButtonIdArray.length; i++) {
            RadioButton answerOption = (RadioButton) findViewById(radioButtonIdArray[i]);
            if (answerOption.isChecked()) {
                if (i == answers[currentQuestion]) {
                    results[currentQuestion] = 1;
                    return;
                }
            }
        }
    }


    /**
     * Clears current question radio button selection before moving to another question
     */

    public void clearSelection () {
        RadioGroup buttonGroup = (RadioGroup) findViewById(R.id.buttonGroup1);
        buttonGroup.clearCheck();
    }


    /**
     * Switches to the results screen and moves makes results available in the results screen
     */

    public void finishQuiz (View view) {
        checkCorrectness();
        Intent finishQuiz = new Intent(this, ResultsActivity.class);
        finishQuiz.putExtra("RESULTS_ARRAY", results);
        startActivity(finishQuiz);
    }

}

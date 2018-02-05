package com.example.android.startuppy;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.android.startuppy.databinding.ActivityBonusBinding;

import java.util.Arrays;

public class BonusActivity extends AppCompatActivity {

    private static final int[] checkboxRefArray = {R.id.checkbox0, R.id.checkbox1, R.id.checkbox2, R.id.checkbox3};
    ActivityBonusBinding binding;
    String[] checkboxOptions;
    int[] checkboxUserAnswers;
    int[] checkboxAnswers;
    String freeFormAnswer;
    int bonusScore;
    String totalPartOneScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bonus);
        totalPartOneScore = getIntent().getStringExtra("TOTAL_CORRECT");
        Resources res = getResources();
        freeFormAnswer = res.getString(R.string.freeFormAnswer);
        checkboxAnswers = res.getIntArray(R.array.checkBoxAnswers);
        checkboxOptions = res.getStringArray(R.array.checkBoxOptionsString);
        for (int i = 0; i < checkboxRefArray.length; i++) {
            CheckBox checkBox = (CheckBox) findViewById(checkboxRefArray[i]);
            checkBox.setText(checkboxOptions[i]);
        }
        int count = checkboxRefArray.length;
        checkboxUserAnswers = new int[count];
    }

    public void checkBonusAnswers(View view) {
        //check if free form is correct
        String freeFormUserAnswer = binding.bonusFreeForm.getText().toString().toLowerCase();
        if (freeFormUserAnswer.equals(freeFormAnswer)) {
            bonusScore += 1;
        }
        //check if checkboxes are correct
        for (int i = 0; i < checkboxRefArray.length; i++) {
            CheckBox checkBox = (CheckBox) findViewById(checkboxRefArray[i]);
            if (checkBox.isChecked()) {
                checkboxUserAnswers[i] = 1;
            }
        }
        if (Arrays.equals(checkboxAnswers, checkboxUserAnswers)) {
            bonusScore += 1;
        }
        //display results and hide button to avoid resubmission
        String bonusResultsMsg = "You got " + bonusScore + " bonus questions and " + totalPartOneScore + " part 1 questions right";
        Toast.makeText(this, bonusResultsMsg, Toast.LENGTH_SHORT).show();
        binding.doneBtn.setVisibility(View.GONE);
    }


}

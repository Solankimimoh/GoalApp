package sukem.com.goalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectGoalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button goalSmokingBtn;
    private Button goalLoseWeightBtn;
    private Button goalExerciseBtn;
    private Button goalMoneySaveBtn;
    private Button customGoalBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_goal);
        initView();
    }

    private void initView() {
        goalSmokingBtn = findViewById(R.id.activity_select_goal_smoking_btn);
        goalLoseWeightBtn = findViewById(R.id.activity_select_goal_loseweight_btn);
        goalExerciseBtn = findViewById(R.id.activity_select_goal_exercise_btn);
        goalMoneySaveBtn = findViewById(R.id.activity_select_goal_moneysave_btn);
        customGoalBtn = findViewById(R.id.activity_select_goal_custome_btn);

        goalSmokingBtn.setOnClickListener(this);
        goalLoseWeightBtn.setOnClickListener(this);
        goalExerciseBtn.setOnClickListener(this);
        goalMoneySaveBtn.setOnClickListener(this);
        customGoalBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_select_goal_smoking_btn:
                smokingGoal();
                break;
            case R.id.activity_select_goal_loseweight_btn:
                loseWeightGoal();
                break;
            case R.id.activity_select_goal_exercise_btn:
                exerciseGoal();
                break;
            case R.id.activity_select_goal_moneysave_btn:
                moneySaveGoal();
                break;
            case R.id.activity_select_goal_custome_btn:
                customGoal();
                break;
        }
    }

    private void customGoal() {
        final Intent gotoCustomeGoalActivity = new Intent(SelectGoalActivity.this, CustomGoalActivity.class);
        startActivity(gotoCustomeGoalActivity);
    }

    private void moneySaveGoal() {
        final Intent gotoMoneySaveInfoActivity = new Intent(SelectGoalActivity.this, MoneySaveInfoActivity.class);
        startActivity(gotoMoneySaveInfoActivity);
    }

    private void exerciseGoal() {
        final Intent gotoExerciseInfoActivity = new Intent(SelectGoalActivity.this, ExerciseInfoActivity.class);
        startActivity(gotoExerciseInfoActivity);
    }

    private void loseWeightGoal() {
        final Intent gotoLoseWeightInfoActivity = new Intent(SelectGoalActivity.this, LoseWeightInfoActivity.class);
        startActivity(gotoLoseWeightInfoActivity);

    }

    private void smokingGoal() {
        final Intent gotoSmokingInfoActivity = new Intent(SelectGoalActivity.this, SmokingInfoActivity.class);
        startActivity(gotoSmokingInfoActivity);
    }
}

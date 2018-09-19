package sukem.com.goalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectGoalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button goalSmokingBtn;
    private Button goalLoseWeightBtn;
    private Button goalExerciseBtn;
    private Button goalMoneySaveBtn;

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

        goalSmokingBtn.setOnClickListener(this);
        goalLoseWeightBtn.setOnClickListener(this);
        goalExerciseBtn.setOnClickListener(this);
        goalMoneySaveBtn.setOnClickListener(this);
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
        }
    }

    private void moneySaveGoal() {

    }

    private void exerciseGoal() {

    }

    private void loseWeightGoal() {

    }

    private void smokingGoal() {

    }
}

package sukem.com.goalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciseInfoActivity extends AppCompatActivity {

    private EditText weekEd;
    private TextView startDateTv;
    private TextView timeTv;
    private Button setGoalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_info);

        initView();

    }

    private void initView() {
        weekEd = findViewById(R.id.activity_exercise_info_week_ed);
        startDateTv = findViewById(R.id.activity_exercise_info_start_tv);
        timeTv = findViewById(R.id.activity_exercise_info_time_tv);
        setGoalBtn = findViewById(R.id.activity_exercise_setgoal_btn);
    }
}

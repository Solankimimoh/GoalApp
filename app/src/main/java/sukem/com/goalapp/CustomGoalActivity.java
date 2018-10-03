package sukem.com.goalapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CustomGoalActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView endTv;
    private TextView startDateTv;
    private TextView timeTv;
    private Button setGoalBtn;
    private int cYear;
    private int cMonth;
    private int cDay;
    private int cHour, sHour;
    private int cMinute, sMinut;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_goal);
        initView();

    }

    private void initView() {

        endTv = findViewById(R.id.activity_custom_goal_end_week_ed);
        startDateTv = findViewById(R.id.activity_custom_goal_start_tv);
        setGoalBtn = findViewById(R.id.activity_custom_goal_setgoal_btn);
        timeTv = findViewById(R.id.activity_custom_goal_time_tv);

        timeTv.setOnClickListener(this);
        startDateTv.setOnClickListener(this);
        endTv.setOnClickListener(this);
        setGoalBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_smoking_info_target_week_ed:
                chooseEndDate();
                break;
            case R.id.activity_smoking_info_start_tv:
                chooseStartDate();
                break;
            case R.id.activity_money_save_info_time_tv:
                chooseTime();
                break;
            case R.id.activity_smoking_info_setgoal_btn:
                setGoal();
                break;

        }
    }

    private void chooseTime() {
        Calendar calendar = Calendar.getInstance();

        cHour = calendar.get(Calendar.HOUR_OF_DAY);
        cMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(CustomGoalActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeTv.setText(hourOfDay + ":" + minute);
                sHour = hourOfDay;
                sMinut = minute;
            }
        }, cHour, cMinute, true);
        timePickerDialog.show();
    }

    private void chooseStartDate() {
        Calendar calendar = Calendar.getInstance();

        cYear = calendar.get(Calendar.YEAR);
        cMonth = calendar.get(Calendar.MONTH);
        cDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(CustomGoalActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startDateTv.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

            }
        }, cYear, cMonth, cDay);

        datePickerDialog.show();
    }

    private void chooseEndDate() {
        Calendar calendar = Calendar.getInstance();

        cYear = calendar.get(Calendar.YEAR);
        cMonth = calendar.get(Calendar.MONTH);
        cDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(CustomGoalActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endTv.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, cYear, cMonth, cDay);

        datePickerDialog.show();
    }

    private void setGoal() {
        Calendar calendar = Calendar.getInstance();
        cHour = calendar.get(Calendar.HOUR_OF_DAY);
        cMinute = calendar.get(Calendar.MINUTE) + 1;

        Toast.makeText(this, "" + cHour + "}" + cMinute, Toast.LENGTH_SHORT).show();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
        calendar.set(Calendar.SECOND, 2);
        Intent intent = new Intent(getApplicationContext(), CustomGoalReceiver.class);
        intent.setAction("MY_NOTIFICATION_MESSAGE");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);

        Toast.makeText(this, "Goal Set Successfully", Toast.LENGTH_SHORT).show();
//        finish();

    }

}

package sukem.com.goalapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailEd;
    private EditText loginPwdEd;
    private Button loginBtn;
    private TextView loginForgotPwdTv;
    private TextView loginNewUserTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        loginEmailEd = findViewById(R.id.activity_login_email_ed);
        loginPwdEd = findViewById(R.id.activity_login_password_ed);
        loginBtn = findViewById(R.id.activity_login_login_btn);
        loginForgotPwdTv = findViewById(R.id.activity_login_forgot_pwd_tv);
        loginNewUserTv = findViewById(R.id.activity_login_new_user_tv);
    }
}

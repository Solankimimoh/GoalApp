package sukem.com.goalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText loginEmailEd;
    private EditText loginPwdEd;
    private Button loginBtn;
    private TextView loginForgotPwdTv;
    private TextView loginNewUserTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        initView();
        if (mAuth.getCurrentUser() != null) {
            final Intent gotoHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(gotoHomeActivity);
            finish();
        }


    }

    private void initView() {
        loginEmailEd = findViewById(R.id.activity_login_email_ed);
        loginPwdEd = findViewById(R.id.activity_login_password_ed);
        loginBtn = findViewById(R.id.activity_login_login_btn);
        loginForgotPwdTv = findViewById(R.id.activity_login_forgot_pwd_tv);
        loginNewUserTv = findViewById(R.id.activity_login_new_user_tv);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Authentication.....");

        loginBtn.setOnClickListener(this);
        loginForgotPwdTv.setOnClickListener(this);
        loginNewUserTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_login_btn:
                loginUser();
                break;
            case R.id.activity_login_forgot_pwd_tv:
                final Intent gotoForgotPwd = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(gotoForgotPwd);
                break;
            case R.id.activity_login_new_user_tv:
                final Intent gotoNewUser = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(gotoNewUser);
                break;
        }

    }

    private void loginUser() {
        progressDialog.show();
        final String email = loginEmailEd.getText().toString().trim();
        final String password = loginPwdEd.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, "Please Enter the Details", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                progressDialog.dismiss();
                                final Intent gotoHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(gotoHomeActivity);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    });
        }

    }
}

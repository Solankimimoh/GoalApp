package sukem.com.goalapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();
    private EditText forgotEmailEd;
    private Button forgotBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initView();
    }

    private void initView() {
        forgotEmailEd = findViewById(R.id.activity_forgot_password_email_ed);
        forgotBtn = findViewById(R.id.activity_forgot_password_btn);
        progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
        progressDialog.setTitle("Forgot password");
        progressDialog.setMessage("Senind reset password email...");
        forgotBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_forgot_password_btn:
                resetPassword();
                break;
        }
    }

    private void resetPassword() {
        final String email = forgotEmailEd.getText().toString().trim();

        if (!email.isEmpty()) {
            FirebaseAuth.getInstance().sendPasswordResetEmail("user@example.com")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.e(TAG, "Email sent.");
                                Toast.makeText(ForgotPasswordActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Enter Email ID", Toast.LENGTH_SHORT).show();
        }

    }
}

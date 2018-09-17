package sukem.com.goalapp;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SignupActivity.class.getSimpleName();
    private EditText signupFirstNameEd;
    private EditText signupLastNameEd;
    private EditText signupEmailEd;
    private EditText signupMobileEd;
    private EditText signupPasswordEd;
    private Button signupBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        initView();

    }

    private void initView() {
        signupFirstNameEd = findViewById(R.id.activity_signup_firstname_ed);
        signupLastNameEd = findViewById(R.id.activity_signup_lastname_ed);
        signupEmailEd = findViewById(R.id.activity_signup_email_ed);
        signupMobileEd = findViewById(R.id.activity_signup_mobile_ed);
        signupPasswordEd = findViewById(R.id.activity_signup_password_ed);
        signupBtn = findViewById(R.id.activity_singup_login_btn);


        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_singup_login_btn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        if (isDataValidate())
        {
            final String firstName = signupFirstNameEd.getText().toString().trim();
            final String lastName = signupLastNameEd.getText().toString().trim();
            final String email = signupEmailEd.getText().toString().trim();
            final String mobile = signupMobileEd.getText().toString().trim();
            final String password = signupPasswordEd.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignupActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    private boolean isDataValidate() {
        final String firstName = signupFirstNameEd.getText().toString().trim();
        final String lastName = signupLastNameEd.getText().toString().trim();
        final String email = signupEmailEd.getText().toString().trim();
        final String mobile = signupMobileEd.getText().toString().trim();
        final String password = signupPasswordEd.getText().toString().trim();

        if (firstName.isEmpty()
                || lastName.isEmpty()
                || email.isEmpty()
                || mobile.isEmpty()
                || password.isEmpty()) {
            Toast.makeText(this, "Please Enter the details", Toast.LENGTH_SHORT).show();

        } else {
            return true;
        }
        return false;
    }
}

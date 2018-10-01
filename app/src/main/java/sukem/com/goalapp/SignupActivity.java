package sukem.com.goalapp;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SignupActivity.class.getSimpleName();
    private EditText signupFirstNameEd;
    private EditText signupLastNameEd;
    private EditText signupEmailEd;
    private EditText signupMobileEd;
    private EditText signupPasswordEd;
    private Button signupBtn;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(AppConstant.FIREBASE_TABLE_USER);

        initView();

    }

    private void initView() {
        signupFirstNameEd = findViewById(R.id.activity_signup_firstname_ed);
        signupLastNameEd = findViewById(R.id.activity_signup_lastname_ed);
        signupEmailEd = findViewById(R.id.activity_signup_email_ed);
        signupMobileEd = findViewById(R.id.activity_signup_mobile_ed);
        signupPasswordEd = findViewById(R.id.activity_signup_password_ed);
        signupBtn = findViewById(R.id.activity_singup_login_btn);
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setTitle("Setting up");
        progressDialog.setMessage("creating account...");
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
        if (isDataValidate()) {

            final String firstName = signupFirstNameEd.getText().toString().trim();
            final String lastName = signupLastNameEd.getText().toString().trim();
            final String email = signupEmailEd.getText().toString().trim();
            final String mobile = signupMobileEd.getText().toString().trim();
            final String password = signupPasswordEd.getText().toString().trim();
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(SignupActivity.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                                    progressDialog.hide();
                                }
                                progressDialog.hide();
                                Toast.makeText(SignupActivity.this, task.getException() + "", Toast.LENGTH_SHORT).show();
                                Log.e("TAG", task.getException() + "");

                            } else {
                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String userId = user.getUid();

                                mDatabase.child(userId)
                                        .setValue(new UserModel(firstName, lastName, email, mobile, password),
                                                new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                                                        if (databaseError != null) {
                                                            Log.e(TAG, databaseError.toException().toString() + "--" + databaseError.getMessage() + databaseError.getCode());
                                                            Toast.makeText(SignupActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
                                                            progressDialog.hide();

                                                        } else {
                                                            Toast.makeText(SignupActivity.this, "Success ! Account Created ", Toast.LENGTH_SHORT).show();
                                                            progressDialog.hide();
                                                            final Intent gotoHomeActivity = new Intent(SignupActivity.this, HomeActivity.class);
                                                            startActivity(gotoHomeActivity);
                                                            finish();

                                                        }
                                                    }
                                                });
                            }
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

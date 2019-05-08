package com.example.studio1bgroup11.edu2;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button registerBtn;
    EditText mEmailField, mUsernameField, mPasswordField, mPasswordField2;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lowercase letter
                    "(?=.*[A-Z])" +         //at least 1 uppercase letter
                    //"(?=.*[a-zA-Z])" +    //any letter
                    //"(?=.*[@#$%^&+=])" +  //at least 1 special character
                    "(?=\\S+$)" +             //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        
        
        mEmailField = findViewById(R.id.emailEditText);
        mPasswordField = findViewById(R.id.passwordEditText);
        mPasswordField2 = findViewById(R.id.passwordAgainEditText);
        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setEnabled(false);


        mPasswordField.addTextChangedListener(registerTextWatcher);
        mEmailField.addTextChangedListener(registerTextWatcher);
        mPasswordField2.addTextChangedListener(registerTextWatcher);
    }

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            registerBtn.setEnabled(validateForm());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            Toast.makeText(RegisterActivity.this, "Failed to register",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);

                            String email = mEmailField.getText().toString();

                            Map<String, Object> dbUser = new HashMap<>();
                            dbUser.put("email", email);
                            db.collection("users").add(dbUser);

                            sendEmailVerification();
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String userName = mUsernameField.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            mUsernameField.setError("Invalid Username.");
            valid = false;
        } else {
            mUsernameField.setError(null);
        }

        String email = mEmailField.getText().toString();
        if(TextUtils.isEmpty(email)) {
            mEmailField.setError("Invalid Email.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password) && !PASSWORD_PATTERN.matcher(password).matches()) {
            mPasswordField.setError("Invalid Password.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        String password2 = mPasswordField2.getText().toString();
        if (!TextUtils.equals(password, password2)) {
            mPasswordField.setError("Passwords don't match.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void sendEmailVerification() {
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this,
                                        "Verification email sent to " + user.getEmail(),
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterActivity.this,
                                        "Failed to send verification email.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.registerBtn) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        }
    }
}




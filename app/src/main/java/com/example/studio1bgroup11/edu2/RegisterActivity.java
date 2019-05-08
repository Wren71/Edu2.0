package com.example.studio1bgroup11.edu2;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button registerBtn;
    EditText emailEt, usernameEt, passwordEt, password2Et;

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
        openHelper = new DatabaseHelper(this);
        emailEt = (EditText) findViewById(R.id.emailEditText);
        usernameEt = (EditText) findViewById(R.id.usernameEditText);
        passwordEt = (EditText) findViewById(R.id.passwordEditText);
        password2Et = (EditText) findViewById(R.id.passwordAgainEditText);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setEnabled(false);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String email = emailEt.getText().toString();
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                String password2 = password2Et.getText().toString();
                if (!password.matches(password2)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                }else if(!isPassword(password)) {
                    Toast.makeText(getApplicationContext(), "Password needs at least 6 characters, one upper case letter, one lower case letter and one number", Toast.LENGTH_LONG).show();
                } else if (!isEmail(email)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email address", Toast.LENGTH_LONG).show();
                } else {
                    insertdata(username, password, email);
                    Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
        usernameEt.addTextChangedListener(registerTextWatcher);
        passwordEt.addTextChangedListener(registerTextWatcher);
        emailEt.addTextChangedListener(registerTextWatcher);
        password2Et.addTextChangedListener(registerTextWatcher);
    }

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String usernameInput = usernameEt.getText().toString().trim();
            String emailInput = passwordEt.getText().toString().trim();
            String passwordInput = passwordEt.getText().toString().trim();
            String password2Input = passwordEt.getText().toString().trim();
            registerBtn.setEnabled(authenticator());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void insertdata(String username, String password, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, username);
        contentValues.put(DatabaseHelper.COL_3, password);
        contentValues.put(DatabaseHelper.COL_4, email);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public boolean authenticator() {
        int count = 0;
        String usernameInput = usernameEt.getText().toString().trim();
        String emailInput = emailEt.getText().toString().trim();
        String passwordInput = passwordEt.getText().toString().trim();
        String password2Input = passwordEt.getText().toString().trim();
        if (!usernameInput.isEmpty()) {
            count++;
        }
        if (!passwordInput.isEmpty()) {
            count++;
        }
        if (!password2Input.isEmpty()) {
            count++;
        }
        if (!emailInput.isEmpty()) {
            count++;
        }
        if (count != 4)
            return false;
        return true;
    }

    boolean isEmail(String email) {
        return (!TextUtils.isEmpty(email)) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean isPassword(String password) {
        return (!TextUtils.isEmpty(password)) && PASSWORD_PATTERN.matcher(password).matches();
    }
}




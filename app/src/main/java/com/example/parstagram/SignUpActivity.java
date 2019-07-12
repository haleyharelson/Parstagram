package com.example.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    //create account button
    private Button createAcct;
    private EditText usernameText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        createAcct();
    }

    private void createAcct() {
        createAcct = findViewById(R.id.createAcct);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);

        createAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newUsername = usernameText.getText().toString();
                String newPassword = passwordText.getText().toString();

                //force user to fill out all fields
                if (newUsername.equals("") || newPassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please fill out both fields.", Toast.LENGTH_LONG).show();
                } else {
                    //save new user data into Parse.com data storage
                    ParseUser user = new ParseUser();
                    user.setUsername(newUsername);
                    user.setPassword(newPassword);

                    Log.v("username: ", newUsername);
                    Log.v("password", newPassword);

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                //show Toast that account creation was successful
                                Toast.makeText(SignUpActivity.this, "Account created successfully!", Toast.LENGTH_LONG).show();
                                //return to login page
                                final Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Error creating account.", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}

package com.example.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    //declare editText and Button objects
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ParseUser currentUser = ParseUser.getCurrentUser();
        checkSession(currentUser);

        //set up Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //link text fields and button with ids
        usernameInput = findViewById(R.id.usernameText);
        passwordInput = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        //when user clicks login button, set user's username and password as constants created from the text fields
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                //call login method
                login(username, password);
            }
        });

        //when user clicks sign up button, launch the sign up screen
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                //finish(); ?
            }
        });
    }

    private void login(String username, String password) {
        //logInBackground ensures that the network call is not happening on the main thread
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                //if there is no error logging in
                if (e == null) {
                    Log.d("LoginActivity", "Login successful!");
                    //create the intent to go from the login screen to the home screen
                    final Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("LoginActivity", "Login failure.");
                    e.printStackTrace();
                }
            }
        });
    }

    //access cached user to determine session status
    public void checkSession(ParseUser currentUser) {
        //if the current user exists after app restart, redirect them to home page
        if (currentUser != null) {
            final Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
        }
    }
}

package com.example.android.whatsappclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    Boolean loginModeActive = false;

    public void redirectIfLoggedIn() {
        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
            startActivity(intent);
        }
    }

    public void toggleLoginMode(View view) {
        Button loginSignupButton = findViewById(R.id.loginSignupButton);
        TextView toggleLoginModeTextView = findViewById(R.id.toggleLoginModeTextView);
        if (loginModeActive) {
            loginModeActive = false;
            loginSignupButton.setText("Kayıt Ol");
            toggleLoginModeTextView.setText("Veya, Giriş Yap");
        } else {
            loginModeActive = true;
            loginSignupButton.setText("Giriş Yap");
            toggleLoginModeTextView.setText("Veya, Kayıt Ol");
        }
    }

    public void signupLogin(View view) {
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        if (loginModeActive) {
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Log.i("Info", "User logged in");
                        redirectIfLoggedIn();
                    } else {
                        String message = e.getMessage();
                        if (message.toLowerCase().contains("java")) {
                            message = e.getMessage().substring(e.getMessage().indexOf(" "));
                        }
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            ParseUser user = new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("Info", "User signed up");
                        redirectIfLoggedIn();
                    } else {
                        String message = e.getMessage();
                        if (message.toLowerCase().contains("java")) {
                            message = e.getMessage().substring(e.getMessage().indexOf(" "));
                        }
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Whatsapp Giriş");

        redirectIfLoggedIn();

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}

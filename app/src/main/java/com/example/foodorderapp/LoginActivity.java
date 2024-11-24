package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        signupButton = findViewById(R.id.buttonSignup);

        // Handle Login Button click
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (validateInputs(username, password)) {
                // Check for specific admin credentials
                if (authenticateUser(username, password)) {
                    // Redirect to Restaurant List Activity
                    Intent intent = new Intent(LoginActivity.this, RestaurantListActivity.class);
                    startActivity(intent);
                    finish(); // Close the Login Activity
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle Signup Button click
        signupButton.setOnClickListener(v -> {
            // Redirect to Signup Activity (create this if needed)
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    // Validate input fields
    private boolean validateInputs(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Check if the username and password are valid admin credentials
    private boolean authenticateUser(String username, String password) {
        // Check for the specific admin credentials
        if ((username.equals("admin") && password.equals("vceadmin")) ||
                (username.equals("admin1") && password.equals("vceadmin1")) ||
                (username.equals("admin2") && password.equals("vceadmin2"))) {
            return true;
        }
        return false;
    }
}

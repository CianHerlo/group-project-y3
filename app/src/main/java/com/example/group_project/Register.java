package com.example.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText emailField, passwordField;
    Button registerBTN, loginTransferBTN;
    FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Variables directed to buttons and text fields
        emailField = findViewById(R.id.emailInputRegister);          // Email Text Input
        passwordField = findViewById(R.id.passwordInputRegister);    // Password Input
        registerBTN = findViewById(R.id.registerBTN);                // Register Button
        loginTransferBTN = findViewById(R.id.loginTransferBTN);      // Button Redirects to Login Page

        // Firebase Authentication
        fireAuth = FirebaseAuth.getInstance();

        if (fireAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailField.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordField.setError("Password is Required.");
                    return;
                }
                if (password.length() < 6) {
                    passwordField.setError("Minimum 6 Characters for Password");
                    return;
                }

                fireAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    } else {
                        Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        loginTransferBTN.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });
    }
}
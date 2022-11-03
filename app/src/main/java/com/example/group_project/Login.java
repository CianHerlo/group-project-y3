package com.example.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailField, passwordField;
    Button registerTransferBTN, loginBTN;
    FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Variables directed to buttons and text fields
        emailField = findViewById(R.id.emailInputLogin);          // Email Text Input
        passwordField = findViewById(R.id.passwordInputLogin);    // Password Input
        registerTransferBTN = findViewById(R.id.registerTransferBTN);                // Register Button
        loginBTN = findViewById(R.id.loginBTN);      // Button Redirects to Login Page

        // Firebase Authentication
        fireAuth = FirebaseAuth.getInstance();

        loginBTN.setOnClickListener(new View.OnClickListener() {
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

                fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        registerTransferBTN.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        });


    }
}
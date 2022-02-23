package com.project.regame.Features.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.project.regame.R;

public class SignUpActivity extends AppCompatActivity {
    MaterialButton btnSignUp;
    TextInputEditText etName, etIdNumber, etEmail, etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupViews();
        setupListeners();
    }
    
    private void setupViews() {
        btnSignUp = findViewById(R.id.btnSignUp);
        etName = findViewById(R.id.etName);
        etIdNumber = findViewById(R.id.etIdNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    private void setupListeners() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInputValid(etName.getText().toString())) {
                    showError(getString(R.string.name_error));
                } else if (!isInputValid(etIdNumber.getText().toString())) {
                    showError(getString(R.string.id_error));
                } else if (!isInputValid(etEmail.getText().toString())) {
                    showError(getString(R.string.email_error));
                } else if (!isInputValid(etPassword.getText().toString()) && !isPasswordValid(etPassword.getText().toString())) {
                    showError(getString(R.string.password_error));
                } else {
                    String name = etName.getText().toString();
                    String idNumber = etIdNumber.getText().toString();
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                }
            }
        });
    }

    private boolean isInputValid(String input) {
        if (input.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isPasswordValid(String inputPassword) {
        if (inputPassword.length() < 6) {
            return false;
        } else {
            return true;
        }
    }

    private void showError(String error) {
        Toast.makeText(SignUpActivity.this, error, Toast.LENGTH_LONG).show();
    }
}

package com.project.regame.Features.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.regame.Features.MainGame;
import com.project.regame.Features.MenuActivity;
import com.project.regame.Features.signup.SignUpActivity;
import com.project.regame.R;

public class SignInActivity extends AppCompatActivity {
    MaterialButton btnSignIn;
    //    MaterialTextView etEmail, etPassword;
    TextInputEditText etEmail;
    TextInputEditText etPassword;
    TextView register;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    CircularProgressIndicator progressIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth = FirebaseAuth.getInstance();
        setupViews();
        setupListeners();
    }

    private void setupViews() {
        btnSignIn = findViewById(R.id.btnSignIn);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        register = findViewById(R.id.register_text);
        progressIndicator = findViewById(R.id.progressCircular);
    }

    private void setupListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressIndicator.setVisibility(View.VISIBLE);
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();
                Boolean hasValidCredentials = validateCredentials(email, password);

                if (hasValidCredentials) {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressIndicator.setVisibility(View.GONE);
                                Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                progressIndicator.setVisibility(View.GONE);
                                showError();
                            }
                        }
                    });
                } else {
                    progressIndicator.setVisibility(View.GONE);
                    showError();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(register);
            }
        });


    }

    private boolean validateCredentials(String email, String password) {
        if (password.isEmpty()) {
            return false;
        }
        if (email.isEmpty()) {
            return false;
        }
        return true;
    }

    private void showError() {
        Toast.makeText(SignInActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
    }
}

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
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
    }

    private void setupListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();
                Boolean hasValidCredentials = validateCredentials(email, password);

                if (hasValidCredentials) {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "Login bay", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {

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
}

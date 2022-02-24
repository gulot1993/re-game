package com.project.regame.Features.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.regame.Features.MenuActivity;
import com.project.regame.Features.model.User;
import com.project.regame.Features.signin.SignInActivity;
import com.project.regame.R;

public class SignUpActivity extends AppCompatActivity {
    MaterialButton btnSignUp;
    TextInputEditText etName, etIdNumber, etEmail, etPassword;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    CircularProgressIndicator progressIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupViews();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        setupListeners();
    }

    private void setupViews() {
        btnSignUp = findViewById(R.id.btnSignUp);
        etName = findViewById(R.id.etName);
        etIdNumber = findViewById(R.id.etIdNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        progressIndicator = findViewById(R.id.progressCircular);
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


                    addDataToFirestore(name, idNumber, email, password);

                }
            }
        });
    }

    private void addDataToFirestore(String name, String idNumber, String email, String password) {
        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference users = db.collection("User");
        User user = new User(name, idNumber, email, password);
        progressIndicator.setVisibility(View.VISIBLE);
        users.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(SignUpActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressIndicator.setVisibility(View.GONE);
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                });
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

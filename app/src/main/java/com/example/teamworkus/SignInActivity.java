package com.example.teamworkus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    Button signInBtn, backBtn;
    EditText email, password;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        signInBtn = findViewById(R.id.signInBtn);
        backBtn = findViewById(R.id.backBtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(TextUtils.isEmpty(email.getText().toString().trim())){
                    email.setError("Enter Email!");
                    Snackbar.make(view,"Enter Email!",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Pattern pattern1 = Pattern.compile( "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");
                Matcher matcher1 = pattern1.matcher(email.getText().toString().trim());
                if (!matcher1.matches()) {
                    email.setError("Error in Email format!");
                    Snackbar.make(view,"Error in Email format!",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString().trim())){
                    password.setError("Enter Password!");
                    Snackbar.make(view,"Enter Password!",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                Pattern pattern2 = Pattern.compile( "^([a-zA-Z])+");
                Matcher matcher2 = pattern2.matcher(password.getText().toString().trim());
                if (!matcher2.matches()) {
                    password.setError("Only alphabets are allowed in password!");
                    Snackbar.make(view,"Only alphabets are allowed in password!",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(view,"Login Failed. Please check your credentials",Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
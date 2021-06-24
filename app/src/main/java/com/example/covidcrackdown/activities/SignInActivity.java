package com.example.covidcrackdown.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidcrackdown.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SignInActivity extends AppCompatActivity {

    private TextView signUpTextView;
    private EditText signInEmail;
    private EditText signInPassword;
//    private TextView forgottenPasswordTextView;
//    private ImageView googleIconImage;
//    private ImageView facebookIconImage;

    private FirebaseAuth mAuth;
    private Button loginButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signUpTextView = findViewById(R.id.text_view_activity_sign_in_sign_up);
        loginButton = findViewById(R.id.activity_main_loginButton);
        signInEmail = findViewById(R.id.edit_text_activity_sign_in_email);
        signInPassword = findViewById(R.id.edit_text_activity_sign_in_password);
//        forgottenPasswordTextView = findViewById(R.id.text_view_activity_forgot_password);
//        googleIconImage = findViewById(R.id.image_view_google_icon);
//        facebookIconImage = findViewById(R.id.image_view_facebook_icon);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signInEmail.getText().toString().trim();
                String password = signInPassword.getText().toString().trim();

                if (email.isEmpty()){
                    signInEmail.setError("Please fill in your email.");
                    return;
                }
                if (password.isEmpty()){
                    signInPassword.setError("Please fill in your password.");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SIGN-IN SUCCEED: ", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("SIGN-IN FAILED", "signInWithEmail:failure", task.getException());
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(SignInActivity.this, "Authentication failed: " + errorMessage,
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {openSignUpActivity();}
        });

        /*facebookIconImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openBackEndFunctionNotImplementedYet();
             }
         });

        googleIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackEndFunctionNotImplementedYet();
            }
        });

        forgottenPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackEndFunctionNotImplementedYet();
            }
        });*/
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }
    }

    private void reload() { }

    private void signInAnonymously() {
        // [START signin_anonymously]
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCCESS", "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FAIL", "signInAnonymously:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END signin_anonymously]
    }

    private void updateUI(FirebaseUser user) {
        openMainPage();
    }

    public void openSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void openMainPage(){
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
        finish();
    }

    /*public void openBackEndFunctionNotImplementedYet(){
        Intent intent = new Intent(this, FunctionNotImplemented.class);
        startActivity(intent);
    }*/

    private long pressedTime;

    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}
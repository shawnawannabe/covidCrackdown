package com.example.covidcrackdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextUserName, editTextSignUpEmail, editTextSignUpPassword, editTextSignUpGender, editTextSignUpAge, editTextSignUpContactNo;
    private Button buttonSignUpCreateAccount;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUserName = findViewById(R.id.edit_text_sign_up_username);
        editTextSignUpEmail = findViewById(R.id.edit_text_sign_up_email);
        editTextSignUpPassword = findViewById(R.id.edit_text_sign_up_password);
        editTextSignUpGender = findViewById(R.id.edit_text_sign_up_gender);
        editTextSignUpAge = findViewById(R.id.edit_text_sign_up_age);
        editTextSignUpContactNo = findViewById(R.id.edit_text_sign_up_contact_no);
        buttonSignUpCreateAccount = findViewById(R.id.button_sign_up_create_account);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        buttonSignUpCreateAccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username = editTextUserName.getText().toString().trim();
                String email = editTextSignUpEmail.getText().toString().trim();
                String password = editTextSignUpPassword.getText().toString().trim();
                String gender = editTextSignUpGender.getText().toString().trim();
//                String age = editTextSignUpAge.getText().toString().trim();
//                String contactNo = editTextSignUpContactNo.getText().toString().trim();

                if (email.isEmpty()){
                    editTextSignUpEmail.setError("Email is required.");
                    return;
                }

                if (password.isEmpty()){
                    editTextSignUpEmail.setError("Email is required.");
                    return;
                }

                if (gender.isEmpty()){
                    editTextSignUpEmail.setError("Email is required.");
                    return;
                }

//                if (age.isEmpty()){
//                    editTextSignUpEmail.setError("Email is required.");
//                    return;
//                }
//
//                if (contactNo.isEmpty()){
//                    editTextSignUpEmail.setError("Email is required.");
//                    return;
//                }
//                TODO: make the validation even better
//                TODO: re-add "age" and contactNo, (removed before because they were integer)
//                if (usernameEditText.getText().length() > 0 && passwordEditText.getText().length() > 0) {
//                    String toastMessage = "Username: " + usernameEditText.getText().toString() + ", Password: " + passwordEditText.getText().toString();
//                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
//                } else {
//                    String toastMessage = "Username or Password are not populated";
//                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
//                }

                createAccount(username, email, password, gender);
            }
        });
    }
//    @IgnoreExtraProperties
//    public class User {
//
//        public String username;
//        public String email;
//        public String password;
//        public String gender;
////        public Integer age;
////        public Integer contactNo;
//        public User() {
//        }
//        public User(String username, String email, String password, String gender) {
//            this.username = username;
//            this.email = email;
//            this.password = password;
//            this.gender = gender;
////            this.age = age;
////            this.contactNo = contactNo;
//        }
//
//    }

    public void openSignIpActivity(){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    private void createAccount(String username, String email, String password, String gender) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCCEED", "createUserWithEmail:success");
//                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid();
                            writeNewUser(userId, username, email, password, gender);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FAILED", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void writeNewUser(String userId, String username, String email, String password, String gender) {
        User user = new User(username, email, password, gender);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private void updateUI(FirebaseUser user){
        openSignIpActivity();
    }
}
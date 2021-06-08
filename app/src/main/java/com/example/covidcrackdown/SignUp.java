package com.example.covidcrackdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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
    private DatabaseReference mDatabase;
    private EditText editTextSignUpUserName, editTextSignUpEmail, editTextSignUpPassword, editTextSignUpAge, editTextSignUpContactNo;
    private Button buttonSignUpCreateAccount;
    private CheckBox signInCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextSignUpUserName = findViewById(R.id.edit_text_sign_up_username);
        editTextSignUpEmail = findViewById(R.id.edit_text_sign_up_email);
        editTextSignUpPassword = findViewById(R.id.edit_text_sign_up_password);
        editTextSignUpAge = findViewById(R.id.edit_text_sign_up_age);
        editTextSignUpContactNo = findViewById(R.id.edit_text_sign_up_contact_no);
        buttonSignUpCreateAccount = findViewById(R.id.button_sign_up_create_account);
        signInCheckbox = findViewById(R.id.check_box_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_list, R.layout.custom_spinner);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        buttonSignUpCreateAccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username = editTextSignUpUserName.getText().toString().trim();
                String email = editTextSignUpEmail.getText().toString().trim();
                String password = editTextSignUpPassword.getText().toString().trim();
                String gender = spinner.getSelectedItem().toString();
                String age = editTextSignUpAge.getText().toString();
                String contactNo = editTextSignUpContactNo.getText().toString();

                if (username.isEmpty()){
                    editTextSignUpUserName.setError("Username is required.");
                    return;
                }
                if (email.isEmpty()){
                    editTextSignUpEmail.setError("Email is required.");
                    return;
                }
                if (password.isEmpty()){
                    editTextSignUpPassword.setError("Password is required.");
                    return;
                }
                if (age.isEmpty()){
                    editTextSignUpAge.setError("Age is required.");
                    return;
                }
                if (contactNo.isEmpty()){
                    editTextSignUpContactNo.setError("Contact number is required.");
                    return;
                }
                if(signInCheckbox.isChecked() == false){
                    signInCheckbox.setError("Agree to the terms and condition to create account");
                    return;
                }
//                TODO: make the validation even better
//                TODO: re-add "age" and contactNo, (removed before because they were integer)
//                if (usernameEditText.getText().length() > 0 && passwordEditText.getText().length() > 0) {
//                    String toastMessage = "Username: " + usernameEditText.getText().toString() + ", Password: " + passwordEditText.getText().toString();
//                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
//                } else {
//                    String toastMessage = "Username or Password are not populated";
//                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
//                }

                // parse age and contact no as int
                int age_value = Integer.parseInt(age);
                int contactNo_value = Integer.parseInt(contactNo);
                createAccount(username, email, password, gender, age_value, contactNo_value);
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

    private void createAccount(String username, String email, String password, String gender, Integer age, Integer contactNo) {
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
                            writeNewUser(userId, username, email, password, gender, age, contactNo);
//                            updateUI(user);
                            Toast.makeText(SignUp.this, "USER CREATED", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FAILED", "createUserWithEmail:failure", task.getException());
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(SignUp.this, "Error: " + errorMessage,
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    public void writeNewUser(String userId, String username, String email, String password, String gender, Integer age, Integer contactNo) {
        User user = new User(username, email, password, gender, age, contactNo);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private void updateUI(FirebaseUser user){
        openSignIpActivity();
    }
}
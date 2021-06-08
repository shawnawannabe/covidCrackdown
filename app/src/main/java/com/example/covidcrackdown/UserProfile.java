package com.example.covidcrackdown;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfile extends Fragment {

    public UserProfile() {
        // Required empty public constructor
    }

    public static UserProfile newInstance(String param1, String param2) {
        UserProfile fragment = new UserProfile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private TextView textViewUsername;
    private TextView textViewEmail;
    private TextView textViewAge;
    private TextView textViewGender;
    private TextView textViewContactNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference mPostReference = mDatabase.child("users/" + user.getUid());
        addPostEventListener(mPostReference);

    }

    private void addPostEventListener(DatabaseReference mPostReference) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                textViewAge.setText(Integer.toString(currentUser.getAge()));
                textViewUsername.setText(currentUser.getUsername());
                textViewGender.setText(currentUser.getGender());
                textViewContactNo.setText(Integer.toString(currentUser.getContactNo()));
                textViewEmail.setText(currentUser.getEmail());
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    User currentUser = snapshot.getValue(User.class);
//                    String str = currentUser.getEmail();
//                    System.out.println("HERE: "+str);
//                 }
//                TODO: remove the lag when changing text in fragment

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FAILED", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_profile, container, false);
        textViewUsername = view.findViewById(R.id.text_view_fragment_user_profile_user);
        textViewEmail = view.findViewById(R.id.text_view_fragment_user_profile_email);
        textViewGender = view.findViewById(R.id.text_view_fragment_user_profile_gender);
        textViewAge = view.findViewById(R.id.text_view_fragment_user_profile_age);
        textViewContactNo = view.findViewById(R.id.text_view_fragment_user_profile_contact_no);

        return view;
    }
}
package com.github.mannie411.hngmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.mannie411.hngmobileapp.model.Login;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    // [START declare_database]
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private EditText nUsername,nPassword;
    private MaterialButton loginBtn,signupBtn;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        nUsername = findViewById(R.id.nUserLogE);
        nPassword = findViewById(R.id.nPasswdLogE);

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        signIn();
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        signupBtn = findViewById(R.id.signup_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    private void signIn(){
        Log.i(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        userId = mFirebaseDatabase.getKey();

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Login user = dataSnapshot.getValue(Login.class);

                // Check for null
                if (user == null) {
                    Log.i(TAG, "User data is null!");
                    return;
                }

                Log.i(TAG, "User data is changed!" + user.getUsername() + ", " + user.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        nUsername.getText().clear();
        nPassword.getText().clear();


    }



    private boolean validateForm() {
        boolean result = true;

        nUsername.setError(null);
        nPassword.setError(null);

        String username = nUsername.getText().toString();
        String password = nPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            nUsername.setError("Required");
            result = false;
        } else {
            nUsername.setError(null);
        }

        if (TextUtils.isEmpty(password)){
            nPassword.setError("Required");
            result = false;
        } else {
            nPassword.setError(null);
        }

        return result;
    }



}

package com.github.mannie411.hngmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mannie411.hngmobileapp.model.Signup;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    // [START declare_database_ref]
    private DatabaseReference Database;

    private TextInputEditText nUsername, nEmail, nPassword;
    private MaterialButton nRegBtn, nLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nUsername = findViewById(R.id.nUserRegE);
        nEmail = findViewById(R.id.emailE);
        nPassword = findViewById(R.id.nPasswdRegL);

        nRegBtn = findViewById(R.id.reg_btn);
        nLoginBtn = findViewById(R.id.login);

        nRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();




            }
        });
        nLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(i);

            }
        });



    }



    private void signUp(){
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        Database = FirebaseDatabase.getInstance().getReference("users");

        String userId = Database.push().getKey();

        String email = nEmail.getText().toString();
        String username = nUsername.getText().toString();
        String password = nPassword.getText().toString();

        Signup user = new Signup(email, username, password);

        Database.child(userId).setValue(user);

        regSuccToast();

        nEmail.getText().clear();
        nUsername.getText().clear();
        nPassword.getText().clear();

    }

    private void regSuccToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Registration Successful");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private boolean validateForm() {
        boolean result = true;

        nEmail.setError(null);
        nUsername.setError(null);
        nPassword.setError(null);

        String email = nEmail.getText().toString();
        String username = nUsername.getText().toString();
        String password = nPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            nEmail.setError("Required");
            result = false;
        } else {
            nEmail.setError(null);
        }

        if (TextUtils.isEmpty(username)) {
            nUsername.setError("Required");
            result = false;
        } else {
            nUsername.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            nPassword.setError("Required");
            result = false;
        } else {
            nPassword.setError(null);
        }

        return result;
    }
}

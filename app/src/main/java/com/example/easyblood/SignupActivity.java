package com.example.easyblood;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText sgnName, sgnPassword, sgnPasswordConfirm, sgnEmail,sgnPhone, sgnAddress;
    Spinner bloodGroupSpinner,locationSpinner;

    Button btnSignup,btnLogin;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        sgnName = findViewById(R.id.signupName);
        sgnPhone = findViewById(R.id.signupPhone);
        sgnEmail = findViewById(R.id.signupEmail);
        sgnPassword = findViewById(R.id.signupPassword);
        sgnPasswordConfirm = findViewById(R.id.confirmSignupPassword);
        bloodGroupSpinner = findViewById(R.id.bloodGroupSpinner);
        locationSpinner = findViewById(R.id.locationSpinner);
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.loginBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void saveData(){
        String name = sgnName.getText().toString().trim();
        String phone = sgnPhone.getText().toString().trim();
        String email = sgnEmail.getText().toString().trim();
        String password = sgnPassword.getText().toString().trim();
        String address = sgnAddress.getText().toString().trim();

        String key = databaseReference.push().getKey();

        User user = new User(name,phone,email,password,address);

        databaseReference.child(key).setValue(user);

        Toast.makeText(this, "SignUp Successfull!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignupActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
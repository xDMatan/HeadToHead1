package com.example.headtohead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
private Button btnSignIn,btnSignUp;
private EditText username,password;
private TextView tvUserNoFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
        btnSignUp= findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        tvUserNoFound = findViewById(R.id.tvUserNotFound);
        tvUserNoFound.setVisibility(View.VISIBLE);





    }

    @Override
    public void onClick(View v) {
        if(v==btnSignIn){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }
        if(v==btnSignUp){
            Intent i = new Intent(this,SignUp.class);
            startActivity(i);

        }

    }
}
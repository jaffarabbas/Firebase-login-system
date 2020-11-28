package com.example.firebase_login_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    ImageButton loginUser;
    EditText loginusername,loginpassword;
    TextView loginval_user,loginval_pass;
    FirebaseAuth loginauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginUser = (ImageButton) findViewById(R.id.loginButton);
        loginusername = (EditText) findViewById(R.id.loginUsername);
        loginpassword = (EditText) findViewById(R.id.loginPassword);
        loginval_user = (TextView) findViewById(R.id.loginvalidation3);
        loginval_pass = (TextView) findViewById(R.id.loginvalidation4);
        loginauth = FirebaseAuth.getInstance();
        this.loginUser.setOnClickListener(new View.OnClickListener() {
            String user,pass;
            @Override
            public void onClick(View v) {
                user = loginusername.getText().toString();
                pass = loginpassword.getText().toString();

                if(user.equals("") && pass.equals("")){
                    loginval_user.setText("Enter Username");
                    loginval_pass.setText("Enter Password");
                }else{
                    LoginUser(user,pass);
                }
            }
        });
    }

    private void LoginUser(String name,String password){
        loginauth.signInWithEmailAndPassword(name,password).addOnSuccessListener(Login.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this,"Login Successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this,Dashboared.class));
            }
        });
    }

//    private void Supply(){
//
//    }
}
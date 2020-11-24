package com.example.firebase_login_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regiester extends AppCompatActivity {
    ImageButton register;
    EditText username,password;
    TextView val_user,val_pass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regiester);

        register = (ImageButton) findViewById(R.id.regButton);
        username = (EditText) findViewById(R.id.regUsername);
        password = (EditText) findViewById(R.id.regPassword);
        val_user = (TextView) findViewById(R.id.loginvalidation);
        val_pass = (TextView) findViewById(R.id.loginvalidation2);

        //firebase declarations
        auth = FirebaseAuth.getInstance();

        this.register.setOnClickListener(new View.OnClickListener() {
            String user,pass;
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("") && pass.equals("")){
                    val_user.setText("Enter Username");
                    val_pass.setText("Enter Password");
                } else if(pass.length() < 6){
                    val_pass.setText("Password Length is Short");
                }else{
                    UserRegistration(user,pass);
                }
            }
        });
    }

    private void UserRegistration(String email,String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Regiester.this,new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Regiester.this,"Register Successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Regiester.this,"Register Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
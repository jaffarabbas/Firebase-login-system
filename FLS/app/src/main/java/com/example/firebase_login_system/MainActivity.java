package com.example.firebase_login_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ImageButton login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (ImageButton) findViewById(R.id.regester);
        login = (ImageButton) findViewById(R.id.login);

//          FirebaseDatabase.getInstance().getReference().child("Students").child("Name2").setValue("Helttlo");
//          HashMap<String,Object> map = new HashMap<>();
//          map.put("Name","Jaffar2");
//          map.put("Email","gam22aportal8@gmail.com");
//          FirebaseDatabase.getInstance().getReference().child("Login2").child("Admin2").updateChildren(map);

        //register redirect
        this.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Regiester.class));
            }
        });

        //login redirect
        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });
    }
}
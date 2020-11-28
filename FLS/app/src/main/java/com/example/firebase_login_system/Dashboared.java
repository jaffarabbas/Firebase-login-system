package com.example.firebase_login_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Dashboared extends AppCompatActivity {
    Button btnsignout,insert,view;
    EditText takedata;
    TextView view_message;
    public static final String TAG = "MyTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboared);

        btnsignout = findViewById(R.id.signout);
        insert = findViewById(R.id.insert_data);
        takedata = findViewById(R.id.writedata);
        view = findViewById(R.id.ShowData);
        view_message = findViewById(R.id.View_Data);

        //InsertData
        this.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = takedata.getText().toString();
                //toast message for successfully insertion
                Toast.makeText(Dashboared.this,"Successfully Inserted",Toast.LENGTH_LONG).show();
                //firebase
                FirebaseDatabase.getInstance().getReference(getAlphaNumericString(10)).setValue(data);
            }
        });
        //View Data
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference(getAlphaNumericString(10)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String data = snapshot.getValue(String.class);
                        view_message.setText(data);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d(TAG,"onFailure: "+error.getMessage());
                    }
                });
            }
        });

        //signOut
        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboared.this,MainActivity.class));
            }
        });
    }

    public String getAlphaNumericString(int n)
    {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
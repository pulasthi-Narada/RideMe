package com.exp.rideme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private Button btnOK,btncan;
    private RadioGroup rg;
    private RadioButton rb;
    private EditText phnumber,password;



    String  num ;
    String passs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnOK=findViewById(R.id.Loginbtn);
        btncan=findViewById(R.id.loginCanselbtn);
        rg = (RadioGroup) findViewById(R.id.Lradiogrop);
        phnumber = (EditText) findViewById(R.id.Loginnumber);
        password = (EditText) findViewById(R.id.LoginPassword);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(radioId);

                 if(LoginValidation()==1){
                     Login();
                 }


            }
        });


        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void Login() {
        if(rb.getText().toString().equalsIgnoreCase("Customer")){
            num = phnumber.getText().toString();
            passs = password.getText().toString();

            DatabaseReference customer_login = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");

            final String n =phnumber.getText().toString();
            final String c ="Customers";
            customer_login.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(num).exists()) {


                        if (Objects.equals(dataSnapshot.child(num).child("password").getValue(), password.getText().toString())){
                            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(LoginActivity.this, CustomerMapsActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else{

            DatabaseReference Driverlogin = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");

            Driverlogin.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(phnumber.getText().toString()).exists()) {


                        if (Objects.equals(dataSnapshot.child(phnumber.getText().toString()).child("password").getValue(), password.getText().toString())) {
                            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, DMapsActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }



    private int LoginValidation() {
        if ((TextUtils.isEmpty(phnumber.getText().toString())) || (TextUtils.isEmpty(password.getText().toString()))){
            Toast.makeText(LoginActivity.this, "input filds can't be blank", Toast.LENGTH_SHORT).show();
            return 0;
        }else {
            return 1;
        }
    }
}
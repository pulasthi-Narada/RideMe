package com.exp.rideme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {
    private Button btnOK,btncan;
    private RadioGroup rg;
    private RadioButton rb;
    private EditText phnumber,password;
    private  int loginNumber;
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

                if(rb.getText().toString().equalsIgnoreCase("Customer")){

                    num = phnumber.getText().toString();
                    passs = password.getText().toString();
                   // DatabaseReference customer_loginPassword = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(num);
                    DatabaseReference customer_loginNumber = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");


                    customer_loginNumber.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(num).exists()){

                                Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                loginNumber =0;
                                Intent intent = new Intent(LoginActivity.this,CActivity.class);
                                startActivity(intent);
                                finish();

                            }else {

                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });






                }else{

                    DatabaseReference Driver_login = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");

                    Driver_login.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(password.getText().toString()).exists()){

                                Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,DMapsActivity.class);
                                startActivity(intent);
                                finish();
                                return;

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
        });


        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }
}
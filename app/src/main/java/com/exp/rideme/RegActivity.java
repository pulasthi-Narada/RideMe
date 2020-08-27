package com.exp.rideme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegActivity extends AppCompatActivity {
    private EditText txtName,txtEmail,txtContact,txtPassword,txtConfirm;
    private Button btnOK,Rbtncansel;
    private RadioGroup rg;
    private RadioButton rb;
    private Customer cus;
    private  Drivers dri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        txtName=findViewById(R.id.txtName);
        txtEmail=findViewById(R.id.txtEmail);
        txtContact=findViewById(R.id.txtContact);
        txtPassword=findViewById(R.id.txtPassword);
        txtConfirm=findViewById(R.id.txtConfirm);
        btnOK=findViewById(R.id.btnOK);
        Rbtncansel = (Button) findViewById(R.id.RbtnCancel);
        rg = (RadioGroup) findViewById(R.id.radiogrop);
        cus = new Customer();
        dri = new Drivers();




        btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {



                            if(RegInputValidation()==1) {

                                SendData();

                }



            }
        });

        Rbtncansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void SendData() {
        int radioId = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radioId);

        if (rb.getText().toString().equalsIgnoreCase("Customer")) {

            DatabaseReference customer_registerCheck = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");


            customer_registerCheck.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if (dataSnapshot.child(txtContact.getText().toString()).exists()) {
                        Toast.makeText(RegActivity.this, "all ready have an account in this number", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(RegActivity.this, "You select Customer", Toast.LENGTH_SHORT).show();


                        DatabaseReference customer_register = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(txtContact.getText().toString());


                        cus.setEmail(txtEmail.getText().toString());
                        cus.setName(txtName.getText().toString());

                        cus.setPassword(txtPassword.getText().toString());

                        customer_register.setValue(cus);
                        Toast.makeText(RegActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegActivity.this, CustomerMapsActivity.class);
                        startActivity(intent);


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else {

            DatabaseReference driver_regsterCheck = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");

            driver_regsterCheck.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(txtContact.getText().toString()).exists()) {
                        Toast.makeText(RegActivity.this, "all ready have an account in this number", Toast.LENGTH_SHORT).show();


                    } else {


                        Toast.makeText(RegActivity.this, "you select Driver", Toast.LENGTH_SHORT).show();

                        DatabaseReference driver_regster = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(txtContact.getText().toString());
                        Intent intent = new Intent(RegActivity.this, DMapsActivity.class);
                        startActivity(intent);

                        dri.setEmail(txtEmail.getText().toString());
                        dri.setName(txtName.getText().toString());

                        dri.setPassword(txtPassword.getText().toString());

                        driver_regster.setValue(dri);
                        Toast.makeText(RegActivity.this, "Register successful", Toast.LENGTH_SHORT).show();

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }



    private int RegInputValidation() {
        if(TextUtils.isEmpty(txtName.getText().toString())){
            Toast.makeText(RegActivity.this, "Name can't be blank", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(TextUtils.isEmpty(txtEmail.getText().toString())){
            Toast.makeText(RegActivity.this, "email can't be blank", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(TextUtils.isEmpty(txtContact.getText().toString())  ){
            Toast.makeText(RegActivity.this, "phone number can't be blank ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(! (txtContact.getText().toString().length()==10)  ){
            Toast.makeText(RegActivity.this, "phone number have 10 digit", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if( TextUtils.isEmpty(txtPassword.getText().toString())  ){
            Toast.makeText(RegActivity.this, "password can't be blank ", Toast.LENGTH_SHORT).show();
            return 0;
        }

        else if(  TextUtils.isEmpty(txtConfirm.getText().toString())) {
            Toast.makeText(RegActivity.this, "Confirm password can't be blank ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if( !(txtPassword.getText().toString().equals(txtConfirm.getText().toString())) ){
            Toast.makeText(RegActivity.this, "password and Confirm password must be same ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else {
            return 1;
        }
    }
}
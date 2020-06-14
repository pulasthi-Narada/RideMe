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





                int radioId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(radioId);
                if(txtPassword.getText().toString().equalsIgnoreCase(txtConfirm.getText().toString())) {

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
                           /*   DatabaseReference cus_id = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");





                             // Read customer id from the database
                             cus_id.addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(DataSnapshot dataSnapshot) {
                                     // This method is called once with the initial value and again
                                     // whenever data at this location is updated.
                                    if(dataSnapshot.exists()){
                                        cusid = (dataSnapshot.getChildrenCount());
                                    }

                                 }


                                 @Override
                                 public void onCancelled(DatabaseError error) {
                                     Toast.makeText(MainActivity.this, "There is a error when reading the customer id", Toast.LENGTH_SHORT).show();

                                 }
                             });*/


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
                                    // Read customer id from the database
                            /* driver_id.addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(DataSnapshot dataSnapshot) {
                                     // This method is called once with the initial value and again
                                     // whenever data at this location is updated.
                                     if(dataSnapshot.exists()){
                                         drId = (dataSnapshot.getChildrenCount());
                                     }

                                 }


                                 @Override
                                 public void onCancelled(DatabaseError error) {
                                     Toast.makeText(MainActivity.this, "There is a error when reading the customer id", Toast.LENGTH_SHORT).show();

                                 }
                             });*/


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
                }else{
                    Toast.makeText(RegActivity.this, "password and confirm password must be same", Toast.LENGTH_SHORT).show();
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
}
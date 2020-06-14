package com.exp.rideme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
    private EditText email,password,name,number;
    private Button ok,cansel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        email = (EditText) findViewById(R.id.StxtEmail);
        password = (EditText) findViewById(R.id.StxtPassword);
        name = (EditText) findViewById(R.id.StxtName);
        number = (EditText) findViewById(R.id.StxtContact);

        ok = (Button) findViewById(R.id.SbtnOK);
        cansel = (Button) findViewById(R.id.SbtnCancel);

       /* ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LoginDetais logd = new LoginDetais();
             //  if((logd.getLoginPhoneNumber()!=null) || (logd.getCatogery()!=null)){
                  String d = logd.getCatogery();
                  String cc = logd.getLoginPhoneNumber();
                //   DatabaseReference LoginDetails= FirebaseDatabase.getInstance().getReference().child("Users").child(logd.getCatogery()).child(logd.getLoginPhoneNumber());
                DatabaseReference LoginDetails= FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child("123456789");
                   LoginDetails.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Toast.makeText(SettingActivity.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
                 /*   LoginDetails.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            number.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                          //  name.setText(dataSnapshot.child("name").getValue().toString());
                          //  email.setText(dataSnapshot.child("email").getValue().toString());
                           // password.setText(dataSnapshot.child("password").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/

                /*   LoginDetails.setValue(number.getText().toString());
                   LoginDetails.child("name").setValue(name.getText().toString());
                   LoginDetails.child("password").setValue(password.getText().toString());
                   LoginDetails.child("email").setValue(email.getText().toString());

                   Toast.makeText(SettingActivity.this, "user details update successfully", Toast.LENGTH_SHORT).show();

                   if(logd.getCatogery()=="Customers"){

                       Intent intent = new Intent(SettingActivity.this, CustomerMapsActivity.class);
                       startActivity(intent);
                       finish();

                   }else {

                       Intent intent = new Intent(SettingActivity.this, DMapsActivity.class);
                       startActivity(intent);
                       finish();
                   }


             //  }else {
             //      Toast.makeText(SettingActivity.this, "can not update user details", Toast.LENGTH_SHORT).show();
             //  }

            }
        });

        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDetais clogd = new LoginDetais();
                if(clogd.getCatogery().equalsIgnoreCase("Customers")){

                    Intent intent = new Intent(SettingActivity.this, CustomerMapsActivity.class);
                    startActivity(intent);
                    finish();

                }else {

                    Intent intent = new Intent(SettingActivity.this, DMapsActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });*/
    }
}
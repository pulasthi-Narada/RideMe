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


        btnOK=findViewById(R.id.Loginbtn);//initializing variables
        btncan=findViewById(R.id.loginCanselbtn);//,,
        rg = (RadioGroup) findViewById(R.id.radiogrop);//,,
        phnumber = (EditText) findViewById(R.id.Loginnumber);//,,
        password = (EditText) findViewById(R.id.LoginPassword);//,,

        btnOK.setOnClickListener(new View.OnClickListener() {//If the button is clicked this event is executed
            @Override
            public void onClick(View v) {
                int radioId = rg.getCheckedRadioButtonId();//Gets the radio button id of the selected option.
                rb = (RadioButton) findViewById(radioId);




                if ((TextUtils.isEmpty(phnumber.getText().toString())) || (TextUtils.isEmpty(password.getText().toString()))){
                    Toast.makeText(LoginActivity.this, "input filds can't be blank", Toast.LENGTH_SHORT).show();
                }





                else if(rb.getText().toString().equalsIgnoreCase("Customer")){//If the customer option is selected

                    num = phnumber.getText().toString();//This variable is converted and assigned to string
                    passs = password.getText().toString();//,,

                    DatabaseReference customer_login = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");//This is the database reference



                    customer_login.addValueEventListener(new ValueEventListener() {//An event which checks if the record exists
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(num).exists()) {//Checks if the phone number exists or not within the database


                                if (Objects.equals(dataSnapshot.child(num).child("password").getValue(), password.getText().toString())){//Checks if the password matches the phone number
                                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();//Shows a message



                                    Intent intent = new Intent(LoginActivity.this, CustomerMapsActivity.class);//After the login process is completed it starts the map activity.
                                    startActivity(intent);

                                }else {
                                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();//If the password is typed incorrectly this message is shown.
                                }

                            }else {
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();//If the phone number is typed incorrectly this message is shown.
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {//If any Database error occurs this method will execute

                        }
                    });






                }else{//If the driver option is selected this else condition is executed

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
                        public void onCancelled(@NonNull DatabaseError databaseError) {//If any Database error occurs this method will execute

                        }
                    });

                }
            }
        });


        btncan.setOnClickListener(new View.OnClickListener() {//If the cancel button is clicked it goes back to the main menu
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);//main menu!
                startActivity(intent);

            }
        });

    }
}
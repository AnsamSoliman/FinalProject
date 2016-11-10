package com.example.ansam.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Registeration extends AppCompatActivity {
    Button register;
    EditText name,email,password,confirmPass;
    String Name,Email,Password,ConfirmPass;
    LinearLayout linearLayout;
    SharedPreferences sh;
    LoginDataBase login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        final Realm myOtherRealm =
                Realm.getInstance(
                        new RealmConfiguration.Builder()
                                .name("myOtherRealm.realm")
                                .build()
                );

       // login=new LoginDataBase(this);
//        login.open();
        linearLayout=(LinearLayout)findViewById(R.id.linearRounded);
        linearLayout.setBackgroundResource(R.drawable.rounded_linear);
        ((GradientDrawable)linearLayout.getBackground()).setColor(Color.parseColor("#ffffff"));
        register=(Button)findViewById(R.id.register);
        name=(EditText)findViewById(R.id.user_signUp);
        email=(EditText)findViewById(R.id.email_signUp);
        password=(EditText)findViewById(R.id.password_signUp);
        confirmPass=(EditText)findViewById(R.id.confirmpass);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name=name.getText().toString();
                Email=email.getText().toString();
                Log.i("email_signUp",Email);
                Password=password.getText().toString();
                Log.i("password_reg",Password);
                ConfirmPass=confirmPass.getText().toString();
                Log.i("password_reg",ConfirmPass);
                if(Name.equals("")||Email.equals("")||Password.equals("")||ConfirmPass.equals(""))
                    Toast.makeText(getApplicationContext(), "Please enter all fields!!", Toast.LENGTH_SHORT).show();
                else if(!Password.equals(ConfirmPass))
                    Toast.makeText(getApplicationContext(), "password and confrim password are not the same!!", Toast.LENGTH_SHORT).show();
                else {
                    //sh=getSharedPreferences("sh",Context.MODE_PRIVATE);
                   // SharedPreferences.Editor editor = sh.edit();
                   // editor.putString("Email",Email);
                   // editor.putString("Password",Password);
                   // editor.commit();
                    // *Saving info in DataBase*//
               //     login.insertEntry(Password,Email);
//                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    myOtherRealm.beginTransaction();
                    Person p=myOtherRealm.createObject(Person.class,Email);
                    p.setEmail(Email);
                    p.setPassword(Password);
                    p.setUserName(Name);
                    myOtherRealm.commitTransaction();
                  Intent i=new Intent(Registeration.this,MainActivity.class);
                    startActivity(i);


                }


            }
        });

    }
}

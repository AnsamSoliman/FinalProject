package com.example.ansam.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registeration extends AppCompatActivity {
    Button register;
    EditText name,email,password,confirmPass;
    String Name,Email,Password,ConfirmPass;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
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
                    sh=getSharedPreferences("sh",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sh.edit();
                    editor.putString("Email",Email);
                    editor.putString("Password",Password);
                    editor.commit();
                    Intent i=new Intent(Registeration.this,MainActivity.class);
                    startActivity(i);

                }


            }
        });
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Action_Man.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
//        register=(Button)findViewById(R.id.register);
//        Typeface myCustomFont =Typeface.createFromAsset(getAssets(),"fonts/Aller_Rg.ttf");
//        register.setTypeface(myCustomFont);
        //  face.setTypeface(myCustomFont2);
        //   register.setTypeface(myCustomFont2);
    }
}

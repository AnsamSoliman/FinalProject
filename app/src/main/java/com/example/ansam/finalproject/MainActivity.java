package com.example.ansam.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView themeDescription, notRegister;
    Button start;
    EditText email, password;
    String em,pass,shPass,shEmail;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sh=getSharedPreferences("sh", Context.MODE_PRIVATE);
        shPass=sh.getString("Password","null");
        shEmail=sh.getString("Email","");
        Log.i("password",shPass);
        Log.i("email",shEmail);
        themeDescription=(TextView)findViewById(R.id.themeDes) ;
        notRegister=(TextView)findViewById(R.id.notRegister);
        start=(Button)findViewById(R.id.start);
        email=(EditText)findViewById(R.id.emai_signIn);
        password=(EditText)findViewById(R.id.password_signIn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em=email.getText().toString();
                Log.i("em",em);
                pass=password.getText().toString();
                if(em.equals("")||pass.equals(""))
                    Toast.makeText(getApplicationContext(), "Please enter all fields!!", Toast.LENGTH_SHORT).show();
                else if(!em.equalsIgnoreCase(shEmail)||!pass.equalsIgnoreCase(shPass))
                    Toast.makeText(getApplicationContext(), "email or password isn't correct!!", Toast.LENGTH_SHORT).show();
                else{
                    Intent i=new Intent(MainActivity.this,Home.class);
                    startActivity(i);
                }




            }
        });

        notRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Registeration.class);
                startActivity(i);
            }
        });
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Action_Man.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
       // Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Bd.ttf");
        // userName.setTypeface(myCustomFont3);
        // title.setTypeface(myCustomFont2);
        start.setTypeface(myCustomFont);
        notRegister.setTypeface(myCustomFont2);
        themeDescription.setTypeface(myCustomFont2);
        email.setTypeface(myCustomFont2);
        password.setTypeface(myCustomFont2);


    }
}

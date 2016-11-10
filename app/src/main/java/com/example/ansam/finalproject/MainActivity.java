package com.example.ansam.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {
    TextView themeDescription, notRegister;
    Button start;
    EditText email, password;
    String em,pass,shPass,shEmail,passw;
    LoginDataBase login;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        Realm myRealm=Realm.getDefaultInstance();
        final Realm myOtherRealm =
                Realm.getInstance(
                        new RealmConfiguration.Builder()
                                .name("myOtherRealm.realm")
                                .build()
                );
     //   login=new LoginDataBase(this);
//        login.open();
       // SharedPreferences sh=getSharedPreferences("sh", Context.MODE_PRIVATE);
       // shPass=sh.getString("Password","null");
       // shEmail=sh.getString("Email","");
//        Log.i("password",shPass);
    //    Log.i("email",shEmail);
        themeDescription=(TextView)findViewById(R.id.themeDes) ;
        notRegister=(TextView)findViewById(R.id.notRegister);
        start=(Button)findViewById(R.id.start);
        email=(EditText)findViewById(R.id.emai_signIn);
        password=(EditText)findViewById(R.id.password_signIn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em=email.getText().toString();
                pass=password.getText().toString();
//                String storedPassword=login.getSinlgeEntry(em);
               // Toast.makeText(MainActivity.this, storedPassword, Toast.LENGTH_LONG).show();
                RealmResults<Person> results1 =
                        myOtherRealm.where(Person.class).equalTo("Email",em).equalTo("Password",pass).findAll();
                if(results1.size()>0)
                {

                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,Home.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_SHORT).show();
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
        start.setTypeface(myCustomFont);
        notRegister.setTypeface(myCustomFont2);
        themeDescription.setTypeface(myCustomFont2);
        email.setTypeface(myCustomFont2);
        password.setTypeface(myCustomFont2);


    }
}

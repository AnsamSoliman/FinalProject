package com.example.ansam.finalproject;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Home extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,ProfileEditFragment.updateInfo{
    Fragment fragment;
    ImageView menu;
    Button circle;
    TextView UserName;
    TextView AboutYou;
    String CommonFriends,Hobbies;
    String [] items,items2;
    LinearLayout LFriends;
    GridView gView;
    ImageView profileImage;
    String hobb[]={"Cooking","Reading","Watching TV","Fashion","Design","Sporting"};
 @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragment=new ProfileEditFragment();
        menu=(ImageView)findViewById(R.id.humb) ;
        UserName=(TextView)findViewById(R.id.user);
        AboutYou=(TextView)findViewById(R.id.aboutU) ;
        LFriends=(LinearLayout)findViewById(R.id.LinearFriend);
        gView=(GridView)findViewById(R.id.gridView);
        profileImage=(ImageView)findViewById(R.id.profileImage);
        gView.setAdapter(new HobbiesButton(this,hobb));
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);

            }
        });

    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_profile:
                getSupportFragmentManager().beginTransaction().add(R.id.home,fragment).commit();
                return true;
            case R.id.settings:
                return true;
        }
        return false;
    }

    @Override
    public void UpdateInformation(boolean flage) {
        if(flage==true){
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(this.openFileInput("TestFolder"));
                profileImage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            SharedPreferences sh=getSharedPreferences("sh",Context.MODE_PRIVATE);
            String user=sh.getString("userName","");
            String about=sh.getString("aboutyou","");
            CommonFriends=sh.getString("friends","");
            Hobbies=sh.getString("hobbies","");
            if(!user.equals(""))UserName.setText(user);
            if(!about.equals(""))AboutYou.setText(about);
            if(!CommonFriends.equals("")) {
                items = CommonFriends.split(",");
                LFriends.removeAllViews();
                for (int i = 0; i < items.length; i++) {
                    String s = items[i];
                    Log.i("items", s);
                    char c = s.charAt(0);
                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    circle=new Button(this);
                    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(80,80);
                    lp.setMargins(0,0,20,0);
                    circle.setLayoutParams(lp);
                    circle.setBackgroundResource(R.drawable.circlr_rounded);
                    ((GradientDrawable)circle.getBackground()).setColor(color);
                    circle.setTextColor(Color.WHITE);
                    circle.setGravity(Gravity.CENTER);
                    circle.setTextSize(15);
                    circle.setText(String.valueOf(c));
                    LFriends.addView(circle);

                }
            }
            if(!Hobbies.equals("")){
                items2=Hobbies.split(",");
                gView.setAdapter(new HobbiesButton(this,items2));

            }
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}


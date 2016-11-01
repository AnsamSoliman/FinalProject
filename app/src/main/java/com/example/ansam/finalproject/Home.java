package com.example.ansam.finalproject;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
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
 @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu=(ImageView)findViewById(R.id.humb) ;
        UserName=(TextView)findViewById(R.id.user);
        AboutYou=(TextView)findViewById(R.id.aboutU) ;
        LFriends=(LinearLayout)findViewById(R.id.LinearFriend);
        gView=(GridView)findViewById(R.id.gridView);
        gView.setAdapter(new HobbiesButton(this));
        HomeFragment homeFragment=new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_home,homeFragment).addToBackStack("home").commit();

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
                fragment=new ProfileEditFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_home,fragment).commit();
                return true;
            case R.id.settings:
                fragment=new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_home,fragment).commit();
                return true;
        }
        return false;
    }

    @Override
    public void UpdateInformation(boolean flage) {
        if(flage==true){
            SharedPreferences sh=getPreferences(Context.MODE_PRIVATE);
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
                    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(75,75);
                    lp.setMargins(0,0,20,0);
                    circle.setLayoutParams(lp);
                    circle.setBackgroundResource(R.drawable.circlr_rounded);
                    ((GradientDrawable)circle.getBackground()).setColor(color);
                    circle.setTextColor(Color.WHITE);
                    circle.setTextSize(15);
                    circle.setText(String.valueOf(c));
                    LFriends.addView(circle);

                }
            }

            if(!Hobbies.equals("")){
                //gView.removeAllViews();
                items2=Hobbies.split(",");
//                gView.removeAllViews();
                gView.setAdapter(new HobbiesButton(this,items2));

            }








        }
    }
}


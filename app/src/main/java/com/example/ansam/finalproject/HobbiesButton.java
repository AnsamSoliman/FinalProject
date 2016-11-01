package com.example.ansam.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ansam on 10/31/2016.
 */

public class HobbiesButton extends BaseAdapter {
    private Context mContext;
    String item[]={"Cooking","Design","Fashion","Sporting","Reading","Watching TV"};
    ArrayList<String> array=new ArrayList<String>(Arrays.asList(item));
    public HobbiesButton(Context c){
        mContext=c;
    }
    public HobbiesButton(Context c,String item[]){
       mContext=c;
        array.clear();
        for(int i=0;i<item.length;i++) {
            array.add( item[i]);
            Log.i("hobbies element:", array.get(i));
        }
    }



    @Override
    public int getCount() {

        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button hobby;
        if(convertView==null){
            hobby=new Button(mContext);
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50);
            hobby.setLayoutParams(lp);
            //hobby.setGravity(Gravity.LEFT);
            hobby.setBackgroundResource(R.drawable.hobby_button);
        }
        else{
            hobby=(Button) convertView;
        }
        hobby.setTextSize(12);
        hobby.setTextColor(Color.WHITE);
        hobby.setTransformationMethod(null);
        hobby.setText( array.get(position));
        return hobby;
    }
}

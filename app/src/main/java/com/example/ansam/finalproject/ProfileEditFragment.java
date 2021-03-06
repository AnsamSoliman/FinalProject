package com.example.ansam.finalproject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static android.app.Activity.RESULT_OK;


public class ProfileEditFragment extends Fragment {
    updateInfo mCallback;
    Boolean flage;
    String []Hobbies;
    String friends="",hobbies="";
    String friend,user,aboutU;
    ArrayAdapter adapter;
    ListView listview;
    EditText userName,commonFriends,aboutYou;
    Button save,enter;
    View view;
    ScrollView scrollView;
    SharedPreferences sharedPreferences;
    SparseBooleanArray sp;
    ImageView imageChooser;
    Bitmap imageBitmap;
    ImageView image;
    final CharSequence[] items = { "Take Photo", "Choose from gallery",
            "Cancel" };


    public interface updateInfo{
        public void UpdateInformation(boolean flage);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initalize views
        view=inflater.inflate(R.layout.fragment_profile_edit, container, false);
        scrollView=(ScrollView)view.findViewById(R.id.scrollview);
        userName=(EditText)view.findViewById(R.id.user);
        aboutYou=(EditText) view.findViewById(R.id.about);
        commonFriends=(EditText)view.findViewById(R.id.friends);
        save=(Button)view.findViewById(R.id.save);
        imageChooser=(ImageView)view.findViewById(R.id.imageChooser);
        //set Adapter for listView
        Hobbies=getResources().getStringArray(R.array.hobbies);
        listview=(ListView)view.findViewById(R.id.list_view);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setAdapter(new ArrayAdapter<String>(view.getContext(),R.layout.list_view,Hobbies));
        //enter common friends
        commonFriends.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    friend=commonFriends.getText().toString();
                    friends+=friend+",";
                    Log.i("Friends",friends);
                    commonFriends.setText("");
                    return true;

                }
                return false;
            }
        });

        //select hobbies

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public AbsListView getListView() {
                return listview;
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sp=getListView().getCheckedItemPositions();
            }
        });
        //select image
        imageChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Take Photo")) {
                            //from camera
                            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);

                        } else if (items[item].equals("Choose from gallery")) {
                            //from gallary
                             Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            pickPhoto.setType("image/*");

                            startActivityForResult( pickPhoto, 1);
                        }
                        else if (items[item].equals("Cancel")) {
                            dialog.dismiss();


                        }
                    }
                });
                builder.show();

            }
        });

        //Save all Data
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp!=null) {
                    for (int i = 0; i < listview.getCount(); i++) {
                        if (sp.valueAt(i))

                            hobbies += Hobbies[sp.keyAt(i)] + ",";
                    }
                }
                Log.i("hobbies:",hobbies);
                sharedPreferences = getActivity().getSharedPreferences("sh",Context.MODE_PRIVATE);
                sharedPreferences.edit().clear();
                aboutU=aboutYou.getText().toString();
                user=userName.getText().toString();
                userName.setEnabled(false);
                commonFriends.setEnabled(false);
                listview.setEnabled(false);
                scrollView.setEnabled(false);
                aboutYou.setEnabled(false);
                save.setEnabled(false);
                userName.setBackgroundColor(Color.parseColor("#BDBDBD"));
                commonFriends.setBackgroundColor(Color.parseColor("#BDBDBD"));
                listview.setBackgroundColor(Color.parseColor("#BDBDBD"));
                aboutYou.setBackgroundColor(Color.parseColor("#BDBDBD"));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", user);
                editor.putString("friends",friends);
                editor.putString("hobbies",hobbies);
                editor.putString("aboutyou",aboutU);
                editor.commit();
                mCallback.UpdateInformation(true);
            }
        });

        return view;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (updateInfo) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHomeActivity");
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK ){
                    Log.i("selectImage","success");
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    image=(ImageView)getActivity().findViewById(R.id.profileImage);
                    image.setImageBitmap(imageBitmap);

                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                   Log.i("select","successFrom Gallary");
                    Uri selectedImage=imageReturnedIntent.getData();
                    image=(ImageView)getActivity().findViewById(R.id.profileImage);
                    image.setImageURI(selectedImage);

                }
                break;
        }
    }


}

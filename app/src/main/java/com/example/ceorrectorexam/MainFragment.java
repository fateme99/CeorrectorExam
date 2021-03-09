package com.example.ceorrectorexam;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


public class MainFragment extends Fragment {
    private Button mButton_take_photo;
    private ImageView mImageView_picture;
    public static final int REQUEST_IMAGE_CAPTURE=1;
    public static final int REQUEST_CODE_CROP=2;
    private Uri picUri;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_main, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==REQUEST_IMAGE_CAPTURE){
                picUri = data.getData();
                performCrop();
            }
            else if (requestCode==REQUEST_CODE_CROP){
                Bundle extras=data.getExtras();
                Bitmap the_pic=extras.getParcelable("data");
                mImageView_picture.setImageBitmap(the_pic);
            }
        }

    }

    private void findViews(View view){
        mButton_take_photo=view.findViewById(R.id.take_photo_btn);
        mImageView_picture=view.findViewById(R.id.pic_view);
    }

    private void setListeners(){
        mButton_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE );
            }
        });
    }

    private void performCrop(){
        try {
            Intent cropIntent=new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri,"image/*");
            cropIntent.putExtra("crop",true);

            cropIntent.putExtra("outputX",256);
            cropIntent.putExtra("outputY",256);
            cropIntent.putExtra("return-data",true);
            startActivityForResult(cropIntent,REQUEST_CODE_CROP);
        }
        catch (ActivityNotFoundException er){
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
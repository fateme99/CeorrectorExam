package com.example.ceorrectorexam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends SingleFragmentActivity {

    public Intent newIntent(Context context){
        Intent intent=new Intent(context , MainActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }
}
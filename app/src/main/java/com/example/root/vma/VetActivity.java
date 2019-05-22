package com.example.root.vma;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VetActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new VetFragment();
    }
}

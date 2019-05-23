package com.example.root.vma;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class VetActivity extends SingleFragmentActivity {

    private static final String EXTRA_VET_ID = "com.example.root.vma.visit_id";

    public static Intent newIntent(Context packegeContext, UUID visitId){
        Intent intent = new Intent(packegeContext, VetActivity.class);
        intent.putExtra(EXTRA_VET_ID, visitId);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID visitID = (UUID) getIntent()
                .getSerializableExtra(EXTRA_VET_ID);
        return VetFragment.newInstance(visitID);
    }
}

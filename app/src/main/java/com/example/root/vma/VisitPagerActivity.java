package com.example.root.vma;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewParent;

import java.util.List;

public class VisitPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Visit> mVisits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_pager);


        mViewPager = (ViewPager) findViewById(R.id.visit_view_pager);

        mVisits = VisitLab.get(this).getVisits();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
               Visit visit = mVisits.get(position);
               return VetFragment.newInstance(visit.getId());
            }

            @Override
            public int getCount() {
               return mVisits.size();
            }
        });

    }
}

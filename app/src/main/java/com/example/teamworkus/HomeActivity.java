package com.example.teamworkus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.teamworkus.fragments.ContactFragment;
import com.example.teamworkus.fragments.ImagesFragment;
import com.example.teamworkus.fragments.ViewImagesFragment;
import com.example.teamworkus.fragments.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(),1);
        viewPageAdapter.addFragment(new ContactFragment(),"ContactUs");
        viewPageAdapter.addFragment(new ImagesFragment(),"Images");
        viewPageAdapter.addFragment(new ViewImagesFragment(),"View Images");
        viewPager.setAdapter(viewPageAdapter);
    }
}
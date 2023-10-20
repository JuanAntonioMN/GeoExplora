package com.example.geoexplora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.viewpager2.widget.ViewPager2;





public class MainActivity extends AppCompatActivity{
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

    }
}
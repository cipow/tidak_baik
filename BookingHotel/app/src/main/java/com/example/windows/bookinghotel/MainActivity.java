package com.example.windows.bookinghotel;

import android.support.annotation.StringDef;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_View);
        viewPager = (ViewPager) findViewById(R.id.my_Pager);

        viewPager.setAdapter(new TabViewAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class TabViewAdapter extends FragmentPagerAdapter {

        String data[] = {"Menu", "About", "History"};

        public TabViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Menu();
                case 1:
                    return new About();
                case 2:
                    return new History();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data[position];
        }
    }
}

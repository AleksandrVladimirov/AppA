package com.example.asvladimirov.appa;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    FragmentPagerAdapter pagerAdapter;

    private static int fragment;
    public static String sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment = 0;
                    return com.example.asvladimirov.appa.testFragment.newInstance(0, "Test");
                case 1:
                    fragment = 1;
                    return com.example.asvladimirov.appa.historyFragment.newInstance(1, "History");
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "Test";
            } else {
                return "History";
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(fragment == 1)
            getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortListDate: {
                sort = "DATE";
            }
            case R.id.sortListStatus: {
                sort = "STATUS";
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

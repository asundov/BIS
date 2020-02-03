package com.example.bis;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class MojiNalaziPageAdapter extends FragmentPagerAdapter {


    private int numberoftabs;


    public MojiNalaziPageAdapter(@NonNull FragmentManager fm, int numberoftabs) {
        super(fm);
        this.numberoftabs = numberoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MojiNalaziTab1();
            case 1:
                return new MojiNalaziTab2();
            case 2:
                return new MojiNalaziTab3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

}




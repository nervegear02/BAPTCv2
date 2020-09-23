package com.example.baptcv2.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.baptcv2.CropList;
import com.example.baptcv2.Ship;


public class viewPagerAdapter extends FragmentPagerAdapter {

    public viewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CropList cropList = new CropList();
                return cropList;
            case 1:
                Ship ship = new Ship();
                return ship;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

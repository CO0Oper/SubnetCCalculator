package com.example.android.subnetcalculator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private Map<Integer, String> mFragmentTags;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mFragmentTags = new HashMap<Integer, String>();
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new addressb_fragment();
            case 1:
                return new addressa_fragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SBA TABLE";
            case 1:
                return "RANGE TABLE";
            default:
                return null;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        Object obj = super.instantiateItem(container, position);
        if(obj instanceof Fragment){
            Fragment f = (Fragment)obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

}
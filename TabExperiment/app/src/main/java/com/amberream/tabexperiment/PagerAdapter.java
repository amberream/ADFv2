package com.amberream.tabexperiment;

import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class PagerAdapter extends FragmentStatePagerAdapter {

    final static String LOG_TAG = PagerAdapter.class.getSimpleName();

    int mNumTabs;

    public PagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.mNumTabs = numTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d(LOG_TAG, "getItem " + i);
        switch (i) {
            case (0):
                return new TabFragment1();
            case (1):
                return new TabFragment2();
            case (2):
                return new TabFragment3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumTabs;
    }
}

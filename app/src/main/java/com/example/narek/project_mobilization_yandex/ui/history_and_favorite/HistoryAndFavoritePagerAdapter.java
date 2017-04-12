package com.example.narek.project_mobilization_yandex.ui.history_and_favorite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list.FavoriteListFragment;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list.HistoryListFragment;

public class HistoryAndFavoritePagerAdapter extends FragmentPagerAdapter {

    // TODO: 12.04.2017 move to recurse
   private String [] mTitles = new String[] {"История","Избрание"};

    public HistoryAndFavoritePagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return HistoryListFragment.newInstance();
            case 1:
                return FavoriteListFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
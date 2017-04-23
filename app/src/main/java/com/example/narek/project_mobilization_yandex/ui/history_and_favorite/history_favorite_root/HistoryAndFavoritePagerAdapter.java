package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_favorite_root;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list.FavoriteListFragment;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list.HistoryListFragment;

class HistoryAndFavoritePagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private Fragment[] mFragments = new Fragment[]{HistoryListFragment.newInstance(), FavoriteListFragment.newInstance()};

    HistoryAndFavoritePagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        mTitles = new String[]{context.getString(R.string.favorite_text), context.getString(R.string.history_text)};
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
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

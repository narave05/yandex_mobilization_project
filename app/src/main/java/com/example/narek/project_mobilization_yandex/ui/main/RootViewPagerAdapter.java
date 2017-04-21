package com.example.narek.project_mobilization_yandex.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_favorite_root.HistoryAndFavoriteRootFragment;
import com.example.narek.project_mobilization_yandex.ui.translate.TranslationFragment;

public class RootViewPagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 2;

    public RootViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TranslationFragment.newInstance();
            case 1:
                return HistoryAndFavoriteRootFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

}

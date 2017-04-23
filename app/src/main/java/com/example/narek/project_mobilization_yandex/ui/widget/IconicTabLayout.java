package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.example.narek.project_mobilization_yandex.R;

public class IconicTabLayout extends TabLayout implements TabLayout.OnTabSelectedListener {

    int icons[] = {R.drawable.ic_tab_translate, R.drawable.ic_tab_fav};
    private int selectedIconColor = Color.BLACK;
    private int unSelectedIconColor = ContextCompat.getColor(getContext(), R.color.bot_tab_un_selected_icon_color);

    public IconicTabLayout(Context context) {
        super(context);
        init();
    }

    public IconicTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        setUpIcons();
        addOnTabSelectedListener(this);
    }

    public void setUpIcons() {
        for (int i = 0; i < icons.length; i++) {
            Tab tab = getTabAt(i);
            if (tab != null) {
                tab.setIcon(icons[i]);
                if (i == 0) {
                    tab.getIcon().setColorFilter(selectedIconColor, PorterDuff.Mode.SRC_IN);
                } else {
                    tab.getIcon().setColorFilter(unSelectedIconColor, PorterDuff.Mode.SRC_IN);
                }
            }


        }
    }

    @Override
    public void onTabSelected(Tab tab) {
        tab.getIcon().setColorFilter(selectedIconColor, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabUnselected(Tab tab) {
        tab.getIcon().setColorFilter(unSelectedIconColor, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabReselected(Tab tab) {

    }
}

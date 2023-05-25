package com.anhql.todolist.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.anhql.todolist.view.frm.FragmentOne;
import com.anhql.todolist.view.frm.FragmentThree;
import com.anhql.todolist.view.frm.FragmentTwo;

public class MyPagerAdapter extends FragmentStateAdapter {

    private final String[] titles = {"Tab 1", "Tab 2", "Tab 3"};

    public MyPagerAdapter(FragmentActivity fa) {
        super(fa);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentTwo();
            case 2:
                return new FragmentThree();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}

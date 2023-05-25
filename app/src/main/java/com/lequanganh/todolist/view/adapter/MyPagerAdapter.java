package com.lequanganh.todolist.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lequanganh.todolist.view.frm.FragmentOne;
import com.lequanganh.todolist.view.frm.FragmentThree;
import com.lequanganh.todolist.view.frm.FragmentTwo;

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

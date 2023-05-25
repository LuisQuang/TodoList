package com.lequanganh.todolist.view.act;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.anhql.todolist.R;
import com.anhql.todolist.databinding.TabLayoutBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lequanganh.todolist.view.adapter.MyPagerAdapter;
import com.lequanganh.todolist.viewmodel.CommonVM;

public class Act extends BaseAct<TabLayoutBinding, CommonVM> {

    @Override
    protected void initViews() {
        binding.viewPager.setAdapter(new MyPagerAdapter(this));
        binding.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.my_green));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            tab.setText("Tab " + (position + 1));
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.ic_add);
                    break;
                case 1:
                    tab.setIcon(R.drawable.ic_accept);
                    break;
                case 2:
                    tab.setIcon(R.drawable.ic_done);
                    break;
            }


        }).attach();
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected TabLayoutBinding initViewBinding() {
        return TabLayoutBinding.inflate(getLayoutInflater());
    }
}

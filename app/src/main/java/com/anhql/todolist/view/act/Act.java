package com.anhql.todolist.view.act;

import androidx.core.content.ContextCompat;

import com.anhql.todolist.R;
import com.anhql.todolist.databinding.TabLayoutBinding;
import com.anhql.todolist.view.adapter.MyPagerAdapter;
import com.anhql.todolist.viewmodel.CommonVM;
import com.google.android.material.tabs.TabLayoutMediator;

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

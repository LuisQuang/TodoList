package com.example.mybasicproject.view.act;

import com.example.mybasicproject.databinding.ActivityMainBinding;
import com.example.mybasicproject.viewmodel.CommonVM;

public class MainActivity extends BaseAct<ActivityMainBinding, CommonVM> {

    @Override
    protected void initViews() {

    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}
package com.anhql.todolist.view.frm;

import android.view.LayoutInflater;

import com.anhql.todolist.databinding.FragmentOneBinding;
import com.anhql.todolist.databinding.FragmentThreeBinding;
import com.anhql.todolist.viewmodel.CommonVM;

public class FragmentThree extends BaseFragment<FragmentThreeBinding, CommonVM> {


    @Override
    protected void initViews() {

    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected FragmentThreeBinding initViewBinding(LayoutInflater inflater) {
        return FragmentThreeBinding.inflate(inflater);
    }
}
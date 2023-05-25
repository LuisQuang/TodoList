package com.lequanganh.todolist.view.frm;

import android.view.LayoutInflater;

import com.anhql.todolist.databinding.FragmentOneBinding;
import com.lequanganh.todolist.viewmodel.CommonVM;

public class FragmentOne extends BaseFragment<FragmentOneBinding, CommonVM> {


    @Override
    protected void initViews() {

    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected FragmentOneBinding initViewBinding(LayoutInflater inflater) {
        return FragmentOneBinding.inflate(inflater);
    }
}
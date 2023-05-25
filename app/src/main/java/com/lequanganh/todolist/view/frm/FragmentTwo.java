package com.lequanganh.todolist.view.frm;

import android.view.LayoutInflater;

import com.anhql.todolist.databinding.FragmentTwoBinding;
import com.lequanganh.todolist.viewmodel.CommonVM;

public class FragmentTwo extends BaseFragment<FragmentTwoBinding, CommonVM> {


    @Override
    protected void initViews() {

    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected FragmentTwoBinding initViewBinding(LayoutInflater inflater) {
        return FragmentTwoBinding.inflate(inflater);
    }
}
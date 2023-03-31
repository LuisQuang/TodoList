package com.example.mybasicproject.view.frm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.mybasicproject.OnMainCallBack;

public abstract class BaseFragment<V extends ViewBinding, M extends ViewModel> extends Fragment implements View.OnClickListener {
    protected V binding;
    protected M viewModel;
    protected Context context;
    protected OnMainCallBack callback;
    private Object data;

    public void setCallback(OnMainCallBack callback) {
        this.callback = callback;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater);
        viewModel = new ViewModelProvider(this).get(getClassVM());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    protected abstract void initViews();

    protected abstract Class<M> getClassVM();

    protected abstract V initViewBinding(LayoutInflater inflater);

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    private void clickView(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
    }
}

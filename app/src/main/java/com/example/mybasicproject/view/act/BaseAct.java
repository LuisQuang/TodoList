package com.example.mybasicproject.view.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.mybasicproject.OnMainCallBack;
import com.example.mybasicproject.R;
import com.example.mybasicproject.view.frm.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseAct<V extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements OnMainCallBack, View.OnClickListener {
    protected V binding;
    protected M viewModel;


    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(getClassVM());
        initViews();
    }

    private void initViews() {
    }

    protected abstract Class<M> getClassVM();

    protected abstract V initViewBinding();

    protected void notify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void notify(int msgID) {
        Toast.makeText(this, msgID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
    }

    @Override
    public void showFragement(String tag, Object data, boolean isBacked) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment<?, ?> baseFragment = (BaseFragment<?, ?>) constructor.newInstance();

            baseFragment.setCallback(this);
            baseFragment.setData(data);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            if (isBacked) {
                trans.addToBackStack(null);
            }
            trans.setCustomAnimations(R.anim.enter, R.anim.exit,
                    R.anim.pop_enter, R.anim.pop_exit).replace(R.id.ln_main, baseFragment, tag).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.anhql.todolist.view.act;

import android.content.Intent;
import android.os.Handler;
import android.view.animation.AnimationUtils;

import com.anhql.todolist.R;
import com.anhql.todolist.databinding.ActivitySplashBinding;
import com.anhql.todolist.viewmodel.CommonVM;

public class Splash extends BaseAct<ActivitySplashBinding, CommonVM> {


    @Override
    protected void initViews() {
        binding.tvQuote.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
        binding.ivLogoSplash.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_enter));
        binding.tvAppName.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_enter));
        new Handler().postDelayed(() -> gotoMain(), 2000);
    }

    private void gotoMain() {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected ActivitySplashBinding initViewBinding() {
        return ActivitySplashBinding.inflate(getLayoutInflater());
    }
}
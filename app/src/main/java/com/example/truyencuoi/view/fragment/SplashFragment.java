package com.example.truyencuoi.view.fragment;

import android.os.Handler;
import com.example.truyencuoi.Constants;
import com.example.truyencuoi.R;
import com.example.truyencuoi.viewmodel.SplashViewModel;

public class SplashFragment extends BaseFragment<SplashViewModel> {

    @Override
    protected Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(this::gotoHome, 2000);
    }


    private void gotoHome(){
        callBack.callBack(Constants.KEY_SHOW_HOME, null);
    }
}

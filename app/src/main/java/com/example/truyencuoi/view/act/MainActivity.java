package com.example.truyencuoi.view.act;

import androidx.appcompat.widget.Toolbar;

import com.example.truyencuoi.Constants;
import com.example.truyencuoi.R;
import com.example.truyencuoi.view.fragment.HomeFragment;
import com.example.truyencuoi.view.fragment.SplashFragment;
import com.example.truyencuoi.view.fragment.StoryDetailFragment;
import com.example.truyencuoi.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<MainViewModel> {

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected void initViews() {
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setCallBack(this);
        showFragment(R.id.container_view, splashFragment, false, 0, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void callBack(String key, Object data) {
        switch (key){
            case Constants.KEY_SHOW_HOME:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setCallBack(this);
                showFragment(R.id.container_view, homeFragment, false, 0, 0);
                break;
            case Constants.KEY_SHOW_STORY_DETAIL:
                StoryDetailFragment storyDetailFragment = new StoryDetailFragment();
                storyDetailFragment.setCallBack(this);
                if (data != null){
                    storyDetailFragment.setStoryIndex((int) data);
                }
                showFragment(R.id.container_view, storyDetailFragment, true, 0, 0);
                break;
        }
    }
}
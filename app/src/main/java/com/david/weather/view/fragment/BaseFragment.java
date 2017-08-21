package com.david.weather.view.fragment;

import android.support.v4.app.Fragment;

import com.david.weather.presenter.BasePresenter;

public abstract class BaseFragment extends android.app.Fragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}


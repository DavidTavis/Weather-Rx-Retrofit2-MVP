package com.david.weather.presenter;

import com.david.weather.model.Model;
import com.david.weather.model.ModelImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    protected Model dataForecast = new ModelImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

}

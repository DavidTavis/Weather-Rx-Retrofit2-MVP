package com.david.weather.other;

import android.app.Application;

import com.david.weather.other.di.DaggerAppComponent;
import com.david.weather.other.di.AppComponent;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }


}

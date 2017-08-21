package com.david.weather.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.david.weather.R;
import com.david.weather.model.api.LocationService;
import com.david.weather.other.di.view.DaggerViewComponent;
import com.david.weather.other.di.view.ViewComponent;
import com.david.weather.other.di.view.ViewDynamicModule;
import com.david.weather.presenter.WeatherPresenter;
import com.david.weather.presenter.vo.Forecast;
import com.david.weather.view.adapter.ForecastListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherFragment extends BaseFragment implements WeatherView {

    @Bind(R.id.weather_forecast_list) ListView listView;
    @Bind(R.id.location_name) TextView locationNameTextView;
    @Bind(R.id.current_temperature) TextView currentTemperatureTextView;
    @Bind(R.id.swipe_refresh_container) SwipeRefreshLayout swipeRefreshLayout;

    private ForecastListAdapter adapter;
    private LocationService locationService;

    private ViewComponent viewComponent;

    @Inject
    protected WeatherPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
        super.onCreate(savedInstanceState);

        locationService = new LocationService(getActivity(),presenter,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);

        adapter = new ForecastListAdapter(new ArrayList<>(), getActivity());
        listView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(() -> {swipeRefreshLayout.setRefreshing(true); updateWeather(); });

        presenter.onCreate(savedInstanceState);

        return view;
    }

    public void setViewComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Override
    public void updateWeather(){
        locationService.onStart();
        makeToast(getResources().getString(R.string.last_update) + " " + presenter.timeUpdate);
    }

    @Override
    public void onStart() {
        super.onStart();
        locationService.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        locationService.onStop();
        presenter.onStop();
    }

    private void makeToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public WeatherPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void stopRefreshing(){
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showForecastList(List<Forecast> forecastList) {
        adapter.setForecastList(forecastList);
    }

    @Override
    public void showCurrentWeather(String location, String temp) {
        locationNameTextView.setText(location);
        currentTemperatureTextView.setText(temp);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}

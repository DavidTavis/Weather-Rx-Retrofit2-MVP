package com.david.weather.model.api;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.david.weather.helper.Util;
import com.david.weather.presenter.WeatherPresenter;
import com.david.weather.view.MainActivity;
import com.david.weather.view.fragment.WeatherFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;

/**
 * Created by TechnoA on 17.08.2017.
 */

public class LocationService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private Context context;
    private Location lastLocation;
    private WeatherPresenter presenter;
    private WeatherFragment view;


    public LocationService(Context context, WeatherPresenter presenter, WeatherFragment view) {
        this.context = context;
        this.presenter = presenter;
        this.view = view;
        buildGoogleApiClient();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        gettingWeather();
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Util.logInfo("Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    private synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void onStart() {
        googleApiClient.connect();
        gettingWeather();
    }

    public void onStop() {
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    private void gettingWeather(){
        if(googleApiClient.isConnected()){
            if(checkLocationPermission()) {
                lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            }
            if (lastLocation != null) {

                presenter.fetchWeatherForecast(lastLocation.getLongitude(), lastLocation.getLatitude(), Locale.getDefault().getLanguage());
            }
            view.stopRefreshing();
        }
    }

    private boolean checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

}

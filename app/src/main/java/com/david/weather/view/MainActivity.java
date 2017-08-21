package com.david.weather.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.david.weather.R;
import com.david.weather.helper.Util;
import com.david.weather.presenter.WeatherPresenter;
import com.david.weather.view.fragment.WeatherFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    private static String TAG = "TAG";
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private android.app.FragmentManager fragmentManager;
    private WeatherFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ButterKnife.bind(this);
        fragmentManager = getFragmentManager();
        fragment = (WeatherFragment) fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new WeatherFragment();
            replaceFragment(fragment, false);
        }
    }

    private void replaceFragment(android.app.Fragment fragment, boolean addBackStack) {
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.change_cityId){
            showInputDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showInputDialog() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
            Util.logInfo(e.toString());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
            Util.logInfo(e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                LatLng latLng = PlaceAutocomplete.getPlace(this, data).getLatLng();

                fragment = (WeatherFragment)fragmentManager.findFragmentByTag(TAG);
                if (fragment == null){
                    fragment = new WeatherFragment();
                    replaceFragment(fragment, false);
                }
                WeatherPresenter presenter = fragment.getPresenter();
                presenter.fetchWeatherForecast(latLng.longitude, latLng.latitude, Locale.getDefault().getLanguage());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Util.logInfo(status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}

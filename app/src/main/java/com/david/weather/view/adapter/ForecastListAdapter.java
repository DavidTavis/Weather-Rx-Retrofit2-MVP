package com.david.weather.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.david.weather.R;
import com.david.weather.helper.DayFormatter;
import com.david.weather.helper.TemperatureFormatter;
import com.david.weather.presenter.vo.Forecast;

import java.util.List;

/**
 * Created by TechnoA on 16.08.2017.
 */

public class ForecastListAdapter extends ArrayAdapter {

    private List<Forecast> forecastList;
    private Context context;

    public ForecastListAdapter(final List<Forecast> forecastList, final Context context) {
        super(context, 0, forecastList);
        this.context = context;
        this.forecastList = forecastList;

    }

    @Override
    public boolean isEnabled(final int position) {
        return false;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.weather_forecast_list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.dayTextView = (TextView) convertView.findViewById(R.id.day);
            viewHolder.descriptionTextView = (TextView) convertView.findViewById(R.id.description);
            viewHolder.maximumTemperatureTextView = (TextView) convertView.findViewById(R.id.maximum_temperature);
            viewHolder.minimumTemperatureTextView = (TextView) convertView.findViewById(R.id.minimum_temperature);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Forecast forecast = (Forecast) getItem(position);

        final DayFormatter dayFormatter = new DayFormatter(context);
        final String day = dayFormatter.format(forecast.getDay());
        viewHolder.dayTextView.setText(day);
        viewHolder.descriptionTextView.setText(forecast.getDescription());
        viewHolder.maximumTemperatureTextView.setText(TemperatureFormatter.format((float)forecast.getMaxTemp()));
        viewHolder.minimumTemperatureTextView.setText(TemperatureFormatter.format((float)forecast.getMinTemp()));

        return convertView;
    }

    /**
     * Cache to avoid doing expensive findViewById() calls for each getView().
     */
    private class ViewHolder {
        private TextView dayTextView;
        private TextView descriptionTextView;
        private TextView maximumTemperatureTextView;
        private TextView minimumTemperatureTextView;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
        clear();
        addAll(forecastList);
    }
}

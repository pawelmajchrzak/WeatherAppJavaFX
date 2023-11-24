package com.test.controller;

import com.test.model.Forecast;
import com.test.model.Weather;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class WeatherScreenController {

    private List<ImageView> imageViews;
    private List<ImageView> dayImageViews;

    private List<Text> fieldsForWeather;
    private List<Text> fieldsForWeatherR;

    private List<List<Text>> allTextFieldsForecast;
    private List<List<Text>> allTextFieldsForecastR;

    public WeatherScreenController(List<ImageView> imageViews, List<ImageView> dayImageViews,
                                   List<Text> fieldsForWeather, List<Text> fieldsForWeatherR,
                                   List<List<Text>> allTextFieldsForecast, List<List<Text>> allTextFieldsForecastR) {
        this.imageViews = imageViews;
        this.dayImageViews = dayImageViews;
        this.fieldsForWeather = fieldsForWeather;
        this.fieldsForWeatherR = fieldsForWeatherR;
        this.allTextFieldsForecast = allTextFieldsForecast;
        this.allTextFieldsForecastR = allTextFieldsForecastR;
    }

    public void displayWeather(Weather weather, ImageView weatherImage, List<Text> fieldsForWeather) {
        fieldsForWeather.get(0).setText("" + String.format("%.0f", weather.getCurrentTemperature()) + " °C");
        fieldsForWeather.get(1).setText(weather.getCityName() + ", teraz " + weather.getTime());
        fieldsForWeather.get(2).setText("" + weather.getDescriptionWeather());
        fieldsForWeather.get(3).setText("Temperatura odczuwalna " + String.format("%.0f", weather.getFeelsLikeTemperature()) + " °C");
        Image image = new Image("https://openweathermap.org/img/wn/" + weather.getIconWeatherCode()+"@2x.png");
        weatherImage.setImage(image);
    }

    public void displayForecast(List<Forecast> forecast, List<List<Text>> textFieldsForecast, List<ImageView> imageViewsTest, List<ImageView> dayImageViewsTest) {
        for (int indexOfForecastFromModel = 0; indexOfForecastFromModel < 8; indexOfForecastFromModel++) {
            Forecast currentForecast = forecast.get(indexOfForecastFromModel);
            // Uzyskaj dostęp do odpowiednich komponentów graficznych
            Text dayAndHourText = textFieldsForecast.get(0).get(indexOfForecastFromModel);
            Text temperatureText = textFieldsForecast.get(1).get(indexOfForecastFromModel);
            Text rainText = textFieldsForecast.get(2).get(indexOfForecastFromModel);
            ImageView imageView = imageViewsTest.get(indexOfForecastFromModel);
            // Ustaw wartości komponentów graficznych
            dayAndHourText.setText(currentForecast.getDateTime());
            Image weatherImage = new Image("https://openweathermap.org/img/wn/" + currentForecast.getIconWeatherCode() + "@2x.png");
            imageView.setImage(weatherImage);
            temperatureText.setText(String.format("%.0f", currentForecast.getTemperature()) + " °C");
            rainText.setText("Opady: " + String.format("%.0f", currentForecast.getProbabilityRain()) + " %");
        }
        int indexOfFieldFromFXML=0;
        for (int indexOfForecastFromModel = 8; indexOfForecastFromModel < 16; indexOfForecastFromModel+=2) {
            Forecast nightForecast = forecast.get(indexOfForecastFromModel);
            Forecast dayForecast = forecast.get(indexOfForecastFromModel+1);
            // Uzyskaj dostęp do odpowiednich komponentów graficznych
            Text dayOfWeek = textFieldsForecast.get(3).get(indexOfFieldFromFXML); //tu powinno być od 0 do 3
            Text dayTemperature = textFieldsForecast.get(4).get(indexOfFieldFromFXML);
            Text nightTemperature = textFieldsForecast.get(5).get(indexOfFieldFromFXML);
            ImageView dayImageView = dayImageViewsTest.get(indexOfFieldFromFXML);

            dayOfWeek.setText(nightForecast.getDateTime());
            nightTemperature.setText("noc " + String.format("%.0f",nightForecast.getTemperature()) + " °C");
            Image weatherImage = new Image("https://openweathermap.org/img/wn/" + dayForecast.getIconWeatherCode() + "@2x.png");
            dayImageView.setImage(weatherImage);
            dayTemperature.setText(String.format("%.0f", dayForecast.getTemperature()) + " °C");
            indexOfFieldFromFXML++;
        }
    }
}


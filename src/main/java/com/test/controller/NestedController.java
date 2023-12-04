package com.test.controller;

import com.test.model.Weather;
import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NestedController implements Initializable {

    @FXML
    private Text DayOfWeek8, DayOfWeek10, DayOfWeek12, DayOfWeek14;
    @FXML
    private TextField cityField, countryField;
    @FXML
    private Label errorCityLabel, errorCountryLabel;
    @FXML
    private Text forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2, forecastDayAndHour3, forecastDayAndHour4, forecastDayAndHour5, forecastDayAndHour6, forecastDayAndHour7;
    @FXML
    private ImageView forecastImage0, forecastImage1, forecastImage2, forecastImage3, forecastImage4, forecastImage5, forecastImage6, forecastImage7, forecastImage9, forecastImage11, forecastImage13, forecastImage15;
    @FXML
    private Text forecastRain0, forecastRain1, forecastRain2, forecastRain3, forecastRain4, forecastRain5, forecastRain6, forecastRain7;
    @FXML
    private Text forecastTemperature0, forecastTemperature1, forecastTemperature2, forecastTemperature3, forecastTemperature4, forecastTemperature5, forecastTemperature6, forecastTemperature7;
    @FXML
    private Text forecastTemperature8, forecastTemperature9, forecastTemperature10, forecastTemperature11, forecastTemperature12, forecastTemperature13, forecastTemperature14, forecastTemperature15;
    @FXML
    private Text NowTimeHourAndMinutes, weatherNowDescription, weatherNowFeelsLike, weatherNowTemperature;
    @FXML
    private ImageView weatherNowImage;
    private List<ImageView> imageViews;
    private List<ImageView> dayImageViews;
    private List<Text> nightTemperatureTexts;
    private List<Text> dayTemperatureTexts;
    private List<Text> dayOfWeekTexts;
    private List<Text> rainTexts;
    private List<Text> temperatureTexts;
    private List<Text> dayAndHourTexts;
    boolean flagCityIsCorrect;
    private WeatherService weatherService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dayAndHourTexts = List.of(forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2, forecastDayAndHour3, forecastDayAndHour4, forecastDayAndHour5, forecastDayAndHour6, forecastDayAndHour7);
        temperatureTexts = List.of(forecastTemperature0, forecastTemperature1, forecastTemperature2, forecastTemperature3, forecastTemperature4, forecastTemperature5, forecastTemperature6, forecastTemperature7);
        rainTexts = List.of(forecastRain0, forecastRain1, forecastRain2, forecastRain3, forecastRain4, forecastRain5, forecastRain6, forecastRain7);
        imageViews = List.of(forecastImage0, forecastImage1, forecastImage2, forecastImage3, forecastImage4, forecastImage5, forecastImage6, forecastImage7);
        dayOfWeekTexts = List.of(DayOfWeek8, DayOfWeek10, DayOfWeek12, DayOfWeek14);
        dayTemperatureTexts = List.of(forecastTemperature9, forecastTemperature11, forecastTemperature13, forecastTemperature15);
        nightTemperatureTexts = List.of(forecastTemperature8, forecastTemperature10, forecastTemperature12, forecastTemperature14);
        dayImageViews = List.of(forecastImage9, forecastImage11, forecastImage13, forecastImage15);
    }

    public void checkWeatherAction() {

        if (fieldsAreValid()) {
            String cityName= cityField.getText();
            String countryName = countryField.getText();

            weatherService = WeatherServiceFactory.createWeatherService();
            flagCityIsCorrect = isCityCorrect(cityName, countryName);
            if (flagCityIsCorrect) {

                Weather weather = weatherService.getWeather(cityName, countryName);
                List<Weather> forecastHourly = weatherService.getForecastHourly(cityName, countryName);
                List<Weather> forecastDaily = weatherService.getForecastDaily(cityName, countryName);

                displayWeather(weather);
                displayForecast(forecastHourly);
            }
        }
    }

    private boolean fieldsAreValid() {
        if(countryField.getText().isEmpty()) {
            errorCountryLabel.setText("Proszę wpisać państwo!");
            return  false;
        }
        if(cityField.getText().isEmpty()) {
            errorCityLabel.setText("Proszę wpisać miasto!");
            return  false;
        }
        errorCityLabel.setText("");
        return  true;
    }

    private boolean isCityCorrect(String cityName, String countryName) {
        if (weatherService.isCityAndCountryValid(cityName, countryName)) {
            errorCountryLabel.setText("");
            errorCityLabel.setText("");
            return true;
        } else {
            errorCountryLabel.setText("Dane dla podanego państwa ");
            errorCityLabel.setText("lub miasta nie są dostępne!");
        }
        return false;
    }

    public void displayWeather(Weather weather) {
        weatherNowTemperature.setText("" + String.format("%.0f", weather.getTemperature()) + " °C");
        NowTimeHourAndMinutes.setText(weather.getCityName() + ", teraz " + weather.getDateTime());
        weatherNowDescription.setText("" + weather.getDescriptionWeather());
        weatherNowFeelsLike.setText("Temperatura odczuwalna " + String.format("%.0f", weather.getFeelsLikeTemperature()) + " °C");
        Image image = new Image("https://openweathermap.org/img/wn/" + weather.getIconWeatherCode()+"@2x.png");
        weatherNowImage.setImage(image);
    }

    public void displayForecast(List<Weather> forecast) {
        for (int indexOfForecastFromModel = 0; indexOfForecastFromModel < 8; indexOfForecastFromModel++) {
            Weather currentForecast = forecast.get(indexOfForecastFromModel);

            Text dayAndHourText = dayAndHourTexts.get(indexOfForecastFromModel);
            Text temperatureText = temperatureTexts.get(indexOfForecastFromModel);
            Text rainText = rainTexts.get(indexOfForecastFromModel);
            ImageView imageView = imageViews.get(indexOfForecastFromModel);

            dayAndHourText.setText(currentForecast.getDateTime());
            Image weatherImage = new Image("https://openweathermap.org/img/wn/" + currentForecast.getIconWeatherCode() + "@2x.png");
            imageView.setImage(weatherImage);
            temperatureText.setText(String.format("%.0f", currentForecast.getTemperature()) + " °C");
            rainText.setText("Opady: " + String.format("%.0f", currentForecast.getProbabilityRain()) + " %");
        }
        int indexOfFieldFromFXML=0;
        for (int indexOfForecastFromModel = 8; indexOfForecastFromModel < 16; indexOfForecastFromModel+=2) {
            Weather nightForecast = forecast.get(indexOfForecastFromModel);
            Weather dayForecast = forecast.get(indexOfForecastFromModel+1);

            Text dayOfWeek = dayOfWeekTexts.get(indexOfFieldFromFXML); //tu powinno być od 0 do 3
            Text dayTemperature = dayTemperatureTexts.get(indexOfFieldFromFXML);
            Text nightTemperature = nightTemperatureTexts.get(indexOfFieldFromFXML);
            ImageView dayImageView = dayImageViews.get(indexOfFieldFromFXML);

            dayOfWeek.setText(nightForecast.getDateTime());
            nightTemperature.setText("noc " + String.format("%.0f",nightForecast.getTemperature()) + " °C");
            Image weatherImage = new Image("https://openweathermap.org/img/wn/" + dayForecast.getIconWeatherCode() + "@2x.png");
            dayImageView.setImage(weatherImage);
            dayTemperature.setText(String.format("%.0f", dayForecast.getTemperature()) + " °C");
            indexOfFieldFromFXML++;
        }
    }



    public void updateCityField(String newCity) {
        cityField.setText(newCity);
    }

    public void updateCountryField(String newCountry) {
        countryField.setText(newCountry);
    }

    public String getCityFieldText() {
        return cityField.getText();
    }

    public String getCountryFieldText() {
        return countryField.getText();
    }

    public boolean getflagCityIsCorrect() {
        return flagCityIsCorrect;
    }
}

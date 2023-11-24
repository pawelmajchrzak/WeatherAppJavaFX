package com.test.controller;

import com.test.CityManager;
import com.test.controller.persistance.CountryAndCity;
import com.test.model.Forecast;
import com.test.model.Weather;
import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import com.test.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class MainViewController extends AbstractController{

    @FXML
    private Text DayOfWeek10;

    @FXML
    private Text DayOfWeek10R;

    @FXML
    private Text DayOfWeek12;

    @FXML
    private Text DayOfWeek12R;

    @FXML
    private Text DayOfWeek14;

    @FXML
    private Text DayOfWeek14R;

    @FXML
    private Text DayOfWeek8;

    @FXML
    private Text DayOfWeek8R;

    @FXML
    private Text NowTimeHourAndMinutes;

    @FXML
    private Text NowTimeHourAndMinutesR;

    @FXML
    private TextField cityField;

    @FXML
    private TextField cityFieldR;

    @FXML
    private TextField countryField;

    @FXML
    private TextField countryFieldR;

    @FXML
    private Label errorCityLabel;

    @FXML
    private Label errorCityLabelR;

    @FXML
    private Label errorCountryLabel;

    @FXML
    private Label errorCountryLabelR;

    @FXML
    private Label errorLabel;

    @FXML
    private Label errorLabel1;

    @FXML
    private Text forecastDayAndHour0;

    @FXML
    private Text forecastDayAndHour0R;

    @FXML
    private Text forecastDayAndHour1;

    @FXML
    private Text forecastDayAndHour1R;

    @FXML
    private Text forecastDayAndHour2;

    @FXML
    private Text forecastDayAndHour2R;

    @FXML
    private Text forecastDayAndHour3;

    @FXML
    private Text forecastDayAndHour3R;

    @FXML
    private Text forecastDayAndHour4;

    @FXML
    private Text forecastDayAndHour4R;

    @FXML
    private Text forecastDayAndHour5;

    @FXML
    private Text forecastDayAndHour5R;

    @FXML
    private Text forecastDayAndHour6;

    @FXML
    private Text forecastDayAndHour6R;

    @FXML
    private Text forecastDayAndHour7;

    @FXML
    private Text forecastDayAndHour7R;

    @FXML
    private ImageView forecastImage0;

    @FXML
    private ImageView forecastImage0R;

    @FXML
    private ImageView forecastImage1;

    @FXML
    private ImageView forecastImage11;

    @FXML
    private ImageView forecastImage11R;

    @FXML
    private ImageView forecastImage13;

    @FXML
    private ImageView forecastImage13R;

    @FXML
    private ImageView forecastImage15;

    @FXML
    private ImageView forecastImage15R;

    @FXML
    private ImageView forecastImage1R;

    @FXML
    private ImageView forecastImage2;

    @FXML
    private ImageView forecastImage2R;

    @FXML
    private ImageView forecastImage3;

    @FXML
    private ImageView forecastImage3R;

    @FXML
    private ImageView forecastImage4;

    @FXML
    private ImageView forecastImage4R;

    @FXML
    private ImageView forecastImage5;

    @FXML
    private ImageView forecastImage5R;

    @FXML
    private ImageView forecastImage6;

    @FXML
    private ImageView forecastImage6R;

    @FXML
    private ImageView forecastImage7;

    @FXML
    private ImageView forecastImage7R;

    @FXML
    private ImageView forecastImage9;

    @FXML
    private ImageView forecastImage9R;

    @FXML
    private Text forecastRain0;

    @FXML
    private Text forecastRain0R;

    @FXML
    private Text forecastRain1;

    @FXML
    private Text forecastRain1R;

    @FXML
    private Text forecastRain2;

    @FXML
    private Text forecastRain2R;

    @FXML
    private Text forecastRain3;

    @FXML
    private Text forecastRain3R;

    @FXML
    private Text forecastRain4;

    @FXML
    private Text forecastRain4R;

    @FXML
    private Text forecastRain5;

    @FXML
    private Text forecastRain5R;

    @FXML
    private Text forecastRain6;

    @FXML
    private Text forecastRain6R;

    @FXML
    private Text forecastRain7;

    @FXML
    private Text forecastRain7R;

    @FXML
    private Text forecastTemperature0;

    @FXML
    private Text forecastTemperature0R;

    @FXML
    private Text forecastTemperature1;

    @FXML
    private Text forecastTemperature10;

    @FXML
    private Text forecastTemperature10R;

    @FXML
    private Text forecastTemperature11;

    @FXML
    private Text forecastTemperature11R;

    @FXML
    private Text forecastTemperature12;

    @FXML
    private Text forecastTemperature12R;

    @FXML
    private Text forecastTemperature13;

    @FXML
    private Text forecastTemperature13R;

    @FXML
    private Text forecastTemperature14;

    @FXML
    private Text forecastTemperature14R;

    @FXML
    private Text forecastTemperature15;

    @FXML
    private Text forecastTemperature15R;

    @FXML
    private Text forecastTemperature1R;

    @FXML
    private Text forecastTemperature2;

    @FXML
    private Text forecastTemperature2R;

    @FXML
    private Text forecastTemperature3;

    @FXML
    private Text forecastTemperature3R;

    @FXML
    private Text forecastTemperature4;

    @FXML
    private Text forecastTemperature4R;

    @FXML
    private Text forecastTemperature5;

    @FXML
    private Text forecastTemperature5R;

    @FXML
    private Text forecastTemperature6;

    @FXML
    private Text forecastTemperature6R;

    @FXML
    private Text forecastTemperature7;

    @FXML
    private Text forecastTemperature7R;

    @FXML
    private Text forecastTemperature8;

    @FXML
    private Text forecastTemperature8R;

    @FXML
    private Text forecastTemperature9;

    @FXML
    private Text forecastTemperature9R;

    @FXML
    private Text weatherNowDescription;

    @FXML
    private Text weatherNowDescriptionR;

    @FXML
    private Text weatherNowFeelsLike;

    @FXML
    private Text weatherNowFeelsLikeR;

    @FXML
    private ImageView weatherNowImage;

    @FXML
    private ImageView weatherNowImageR;

    @FXML
    private Text weatherNowTemperature;

    @FXML
    private Text weatherNowTemperatureR;

    private List<ImageView> imageViews;
    private List<ImageView> dayImageViews;
    private List<ImageView> imageViewsR;
    private List<ImageView> dayImageViewsR;

    private List<Text> fieldsForWeather;
    private List<Text> fieldsForWeatherR;

    List<List<Text>> allTextFieldsForecast = new ArrayList<>();
    List<List<Text>> allTextFieldsForecastR = new ArrayList<>();

    @FXML
    private void initialize() {
        List<Text> dayAndHourTexts = List.of(forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2, forecastDayAndHour3, forecastDayAndHour4, forecastDayAndHour5, forecastDayAndHour6, forecastDayAndHour7);
        List<Text> temperatureTexts = List.of(forecastTemperature0, forecastTemperature1, forecastTemperature2, forecastTemperature3, forecastTemperature4, forecastTemperature5, forecastTemperature6, forecastTemperature7);
        List<Text> rainTexts = List.of(forecastRain0, forecastRain1, forecastRain2, forecastRain3, forecastRain4, forecastRain5, forecastRain6, forecastRain7);
        imageViews = List.of(forecastImage0, forecastImage1, forecastImage2, forecastImage3, forecastImage4, forecastImage5, forecastImage6, forecastImage7);
        List<Text> dayOfWeekTexts = List.of(DayOfWeek8, DayOfWeek10, DayOfWeek12, DayOfWeek14);
        List<Text> dayTemperatureTexts = List.of(forecastTemperature9, forecastTemperature11, forecastTemperature13, forecastTemperature15);
        List<Text> nightTemperatureTexts = List.of(forecastTemperature8, forecastTemperature10, forecastTemperature12, forecastTemperature14);
        dayImageViews = List.of(forecastImage9, forecastImage11, forecastImage13, forecastImage15);
        allTextFieldsForecast.add(dayAndHourTexts);
        allTextFieldsForecast.add(temperatureTexts);
        allTextFieldsForecast.add(rainTexts);
        allTextFieldsForecast.add(dayOfWeekTexts);
        allTextFieldsForecast.add(dayTemperatureTexts);
        allTextFieldsForecast.add(nightTemperatureTexts);

        List<Text> dayAndHourTextsR = List.of(forecastDayAndHour0R, forecastDayAndHour1R, forecastDayAndHour2R, forecastDayAndHour3R, forecastDayAndHour4R, forecastDayAndHour5R, forecastDayAndHour6R, forecastDayAndHour7R);
        List<Text> temperatureTextsR = List.of(forecastTemperature0R, forecastTemperature1R, forecastTemperature2R, forecastTemperature3R, forecastTemperature4R, forecastTemperature5R, forecastTemperature6R, forecastTemperature7R);
        List<Text> rainTextsR = List.of(forecastRain0R, forecastRain1R, forecastRain2R, forecastRain3R, forecastRain4R, forecastRain5R, forecastRain6R, forecastRain7R);
        imageViewsR = List.of(forecastImage0R, forecastImage1R, forecastImage2R, forecastImage3R, forecastImage4R, forecastImage5R, forecastImage6R, forecastImage7R);
        List<Text> dayOfWeekTextsR = List.of(DayOfWeek8R, DayOfWeek10R, DayOfWeek12R, DayOfWeek14R);
        List<Text> dayTemperatureTextsR = List.of(forecastTemperature9R, forecastTemperature11R, forecastTemperature13R, forecastTemperature15R);
        List<Text> nightTemperatureTextsR = List.of(forecastTemperature8R, forecastTemperature10R, forecastTemperature12R, forecastTemperature14R);
        dayImageViewsR = List.of(forecastImage9R, forecastImage11R, forecastImage13R, forecastImage15R);
        allTextFieldsForecastR.add(dayAndHourTextsR);
        allTextFieldsForecastR.add(temperatureTextsR);
        allTextFieldsForecastR.add(rainTextsR);
        allTextFieldsForecastR.add(dayOfWeekTextsR);
        allTextFieldsForecastR.add(dayTemperatureTextsR);
        allTextFieldsForecastR.add(nightTemperatureTextsR);

        fieldsForWeather = List.of(weatherNowTemperature, NowTimeHourAndMinutes, weatherNowDescription, weatherNowFeelsLike);
        fieldsForWeatherR = List.of(weatherNowTemperatureR, NowTimeHourAndMinutesR, weatherNowDescriptionR, weatherNowFeelsLikeR);

        if (cityManager != null) {
            List<CountryAndCity> loadedCityData = cityManager.getCityData();
            if (loadedCityData != null && !loadedCityData.isEmpty()) {
                // Zrobić else - i inny widok wczytać!!! - tylko do zebrania miasta i państwa albo schować wszystko i wstawić jakieś poczekadło
                CountryAndCity firstCity = loadedCityData.get(0);
                countryField.setText(firstCity.getCountry());
                cityField.setText(firstCity.getCity());
                CountryAndCity secondCity = loadedCityData.get(1);
                countryFieldR.setText(secondCity.getCountry());
                cityFieldR.setText(secondCity.getCity());
            }
        }
        checkWeatherAction();
    }


    private WeatherService weatherService;

    public MainViewController(CityManager cityManager, ViewFactory viewFactory, String fxmlName) {
        super(cityManager, viewFactory, fxmlName);
    }

    @FXML
    void checkWeatherAction() {
        if (fieldsAreValid()) {

            //Get Data input from user
            String cityName= cityField.getText();
            String countryName = countryField.getText();
            String side = "lewa";

            String cityNameR= cityFieldR.getText();
            String countryNameR = countryFieldR.getText();
            String sideR = "prawa";

            //Invoke business logic
            weatherService = WeatherServiceFactory.createWeatherService();
            //displayWeatherAndForecast(cityName, countryName, side);
            boolean flagCityIsCorrect = isCityCorrect(cityName, countryName);
            boolean flagCityIsCorrectR = isCityCorrect(cityNameR, countryNameR);

            if (flagCityIsCorrect&&flagCityIsCorrectR) {
                cityManager.removeLastCityData();
                cityManager.removeLastCityData();
                cityManager.addCityData(new CountryAndCity(countryName,cityName));
                cityManager.addCityData(new CountryAndCity(countryNameR,cityNameR));

                //Pobieranie danych z Rest api
                Weather weather = weatherService.getWeather(cityName, countryName);
                List<Forecast> forecast = weatherService.getForecast(cityName, countryName);
                Weather weatherR = weatherService.getWeather(cityNameR, countryNameR);
                List<Forecast> forecastR = weatherService.getForecast(cityNameR, countryNameR);

                //Pokazywanie danych
                displayWeather(weather, fieldsForWeather, weatherNowImage);
                displayWeather(weatherR, fieldsForWeatherR, weatherNowImageR);
                displayForecast(forecast, allTextFieldsForecast, imageViews, dayImageViews);
                displayForecast(forecastR, allTextFieldsForecastR, imageViewsR, dayImageViewsR);
            }
//            if (weatherService.isCityAndCountryValid(cityName, countryName)) {
//                errorCityLabel.setText("");
//                errorCountryLabel.setText("");
//                cityManager.removeLastCityData();
//                cityManager.addCityData(new CountryAndCity(countryName,cityName));
//                Weather weather = weatherService.getWeather(cityName, countryName);
//                List<Forecast> forecast = weatherService.getForecast(cityName, countryName);
//
//                //Display result from business logic
//                displayWeather(weather);
//                displayForecast(forecast);
//            } else {
//                errorCountryLabel.setText("Dane dla podanego państwa ");
//                errorCityLabel.setText("lub miasta nie są dostępne!");
//            }
        }
    }

    private boolean isCityCorrect(String cityName, String countryName) {
        if (weatherService.isCityAndCountryValid(cityName, countryName)) {
            errorCityLabel.setText("");
            errorCountryLabel.setText("");
            return true;
        } else {
            errorCountryLabel.setText("Dane dla podanego państwa ");
            errorCityLabel.setText("lub miasta nie są dostępne!");
        }
        return false;
    }

    private String getCityName() {
        if(fieldsAreValid()) {
            return cityField.getText();
        } else {
            return "";
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

//    private void displayWeatherAndForecast(String cityName, String countryName, String sideOfDisplay) {
//        if (weatherService.isCityAndCountryValid(cityName, countryName)) {
//            errorCityLabel.setText("");
//            errorCountryLabel.setText("");
//            cityManager.removeLastCityData();
//            cityManager.addCityData(new CountryAndCity(countryName, cityName));
//            Weather weather = weatherService.getWeather(cityName, countryName);
//            List<Forecast> forecast = weatherService.getForecast(cityName, countryName);
//
//            displayWeather(weather, fieldsForWeather, weatherNowImage);
//            displayForecast(forecast, allTextFieldsForecast);
//
//        } else {
//            errorCountryLabel.setText("Dane dla podanego państwa ");
//            errorCityLabel.setText("lub miasta nie są dostępne!");
//        }
//    }


    private void displayForecast(List<Forecast> forecast,List<List<Text>> textFieldsForecast, List<ImageView> imageViewsTest, List<ImageView> dayImageViewsTest) {
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

    private void displayWeather(Weather weather, List<Text> fieldsForWeather, ImageView weatherImage) {
        fieldsForWeather.get(0).setText("" + String.format("%.0f", weather.getCurrentTemperature()) + " °C");
        //weatherNowTemperature.setText("" + String.format("%.0f", weather.getCurrentTemperature()) + " °C");
        fieldsForWeather.get(1).setText(weather.getCityName() + ", teraz " + weather.getTime());
        fieldsForWeather.get(2).setText("" + weather.getDescriptionWeather());
        fieldsForWeather.get(3).setText("Temperatura odczuwalna " + String.format("%.0f", weather.getFeelsLikeTemperature()) + " °C");
        Image image = new Image("https://openweathermap.org/img/wn/" + weather.getIconWeatherCode()+"@2x.png");
        weatherImage.setImage(image);
    }
}

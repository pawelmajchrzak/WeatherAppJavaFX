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

    private List<Text> dayAndHourTexts;
    private List<ImageView> imageViews;
    private List<Text> temperatureTexts;
    private List<Text> rainTexts;
    private List<Text> dayOfWeekTexts;
    private List<Text> dayTemperatureTexts;
    private List<Text> nightTemperatureTexts;
    private List<ImageView> dayImageViews;

    @FXML
    private void initialize() {
        dayAndHourTexts = List.of(forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2, forecastDayAndHour3, forecastDayAndHour4, forecastDayAndHour5, forecastDayAndHour6, forecastDayAndHour7);
        imageViews = List.of(forecastImage0, forecastImage1, forecastImage2, forecastImage3, forecastImage4, forecastImage5, forecastImage6, forecastImage7);
        temperatureTexts = List.of(forecastTemperature0, forecastTemperature1, forecastTemperature2, forecastTemperature3, forecastTemperature4, forecastTemperature5, forecastTemperature6, forecastTemperature7);
        rainTexts = List.of(forecastRain0, forecastRain1, forecastRain2, forecastRain3, forecastRain4, forecastRain5, forecastRain6, forecastRain7);

        dayOfWeekTexts = List.of(DayOfWeek8, DayOfWeek10, DayOfWeek12, DayOfWeek14);
        dayTemperatureTexts = List.of(forecastTemperature9, forecastTemperature11, forecastTemperature13, forecastTemperature15);
        nightTemperatureTexts = List.of(forecastTemperature8, forecastTemperature10, forecastTemperature12, forecastTemperature14);
        dayImageViews = List.of(forecastImage9, forecastImage11, forecastImage13, forecastImage15);

        if (cityManager != null) {
            List<CountryAndCity> loadedCityData = cityManager.getCityData();
            if (loadedCityData != null && !loadedCityData.isEmpty()) {
                // Jeśli wczytane dane istnieją, możesz użyć ich w dowolny sposób, na przykład, ustawiając domyślne wartości w polach tekstowych
                CountryAndCity firstCity = loadedCityData.get(0);
                countryField.setText(firstCity.getCountry());
                cityField.setText(firstCity.getCity());
            }
        }
        checkWeatherAction();
    }


    private WeatherService weatherService;
    //CityManager cityManager;

    public MainViewController(CityManager cityManager, ViewFactory viewFactory, String fxmlName) {
        super(cityManager, viewFactory, fxmlName);
    }

    @FXML
    void checkWeatherAction() {
        System.out.println("weatherChecked !!!");
        if (fieldsAreValid()) {

            //Get Data input from user
            String cityName= cityField.getText();
            String countryName = countryField.getText();

            //Invoke business logic
            weatherService = WeatherServiceFactory.createWeatherService();
            if (weatherService.isCityAndCountryValid(cityName, countryName)) {
                errorCityLabel.setText("");
                errorCountryLabel.setText("");
                //cityManager = new CityManager();
                cityManager.removeLastCityData();
                cityManager.addCityData(new CountryAndCity(countryName,cityName));
                Weather weather = weatherService.getWeather(cityName, countryName);
                List<Forecast> forecast = weatherService.getForecast(cityName, countryName);

                //Display result from business logic
                displayWeather(weather);
                displayForecast(forecast);
            } else {
                errorCountryLabel.setText("Dane dla podanego państwa ");
                errorCityLabel.setText("lub miasta nie są dostępne!");
            }
        }
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


    private void displayForecast(List<Forecast> forecast) {
        for (int indexOfForecastFromModel = 0; indexOfForecastFromModel < 8; indexOfForecastFromModel++) {
            Forecast currentForecast = forecast.get(indexOfForecastFromModel);
            // Uzyskaj dostęp do odpowiednich komponentów graficznych
            Text dayAndHourText = dayAndHourTexts.get(indexOfForecastFromModel);
            ImageView imageView = imageViews.get(indexOfForecastFromModel);
            Text temperatureText = temperatureTexts.get(indexOfForecastFromModel);
            Text rainText = rainTexts.get(indexOfForecastFromModel);
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
            Text dayOfWeek = dayOfWeekTexts.get(indexOfFieldFromFXML); //tu powinno być od 0 do 3
            ImageView dayImageView = dayImageViews.get(indexOfFieldFromFXML);
            Text dayTemperature = dayTemperatureTexts.get(indexOfFieldFromFXML);
            Text nightTemperature = nightTemperatureTexts.get(indexOfFieldFromFXML);

            dayOfWeek.setText(nightForecast.getDateTime());
            nightTemperature.setText("noc " + String.format("%.0f",nightForecast.getTemperature()) + " °C");
            Image weatherImage = new Image("https://openweathermap.org/img/wn/" + dayForecast.getIconWeatherCode() + "@2x.png");
            dayImageView.setImage(weatherImage);
            dayTemperature.setText(String.format("%.0f", dayForecast.getTemperature()) + " °C");
            indexOfFieldFromFXML++;
        }
    }

    private void displayWeather(Weather weather) {
        weatherNowTemperature.setText("" + String.format("%.0f", weather.getCurrentTemperature()) + " °C");
        NowTimeHourAndMinutes.setText(weather.getCityName() + ", teraz " + weather.getTime());
        weatherNowDescription.setText("" + weather.getDescriptionWeather());
        weatherNowFeelsLike.setText("Temperatura odczuwalna " + String.format("%.0f", weather.getFeelsLikeTemperature()) + " °C");
        Image image = new Image("https://openweathermap.org/img/wn/" + weather.getIconWeatherCode()+"@2x.png");
        weatherNowImage.setImage(image);
    }
}

package com.bbynum.tempmon.managers;

import com.bbynum.tempmon.entities.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherManager {
    private static final Logger log = LogManager.getLogger(WeatherManager.class);

    /**
     * Returns current date and time formatted for MySQL DATETIME column
     * @return String
     */
    public String dateTimeStamp() {
        log.info("WeatherManager::dateTimeStamp Retrieving current datetime");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Creates Weather object with rounded temperature and humidity - Should
     * always be used to create Weather object
     * @param temp Temperature
     * @param humidity Humidity
     * @return Weather
     */
    public Weather generateRoundedWeather(double temp, double humidity) {
        log.info("WeatherManager::generateRoundedWeather Generating new Weather() with rounded temp/humidity");
        return new Weather(roundDouble(temp), roundDouble(humidity));
    }

    /**
     * Rounds temperature and humidity of a Weather object
     * @param weather Weather
     * @return Weather
     */
    public Weather roundWeather(Weather weather) {
        log.info("WeatherManager::roundWeather Rounding temp/humidity of Weather() object");
        weather.setTempF(roundDouble(weather.getTempF()));
        weather.setHumidity(roundDouble(weather.getHumidity()));
        return weather;
    }

    /**
     * Rounds a double to two decimal places
     * @param dub Double
     * @return Double
     */
    public double roundDouble (double dub) {
        log.info(String.format("WeatherManager::roundDouble Rounding double %s", dub));
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        return Double.parseDouble(decimalFormat.format(dub));
    }

}

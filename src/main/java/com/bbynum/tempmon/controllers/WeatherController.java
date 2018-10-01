package com.bbynum.tempmon.controllers;

import com.bbynum.tempmon.managers.WeatherManager;
import com.bbynum.tempmon.repositories.WeatherRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {
    private static final Logger log = LogManager.getLogger(WeatherController.class);

    private WeatherManager weatherManager;
    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherController(WeatherManager weatherManager, WeatherRepository weatherRepository) {
        this.weatherManager = weatherManager;
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/save-temp/{temp}/{humidity}")
    public void saveTemp(@PathVariable Double temp, @PathVariable Double humidity) {
        log.info("WeatherController::saveTemp Parameters received - processing");
        weatherRepository.save(weatherManager.generateRoundedWeather(temp, humidity));
    }

    @GetMapping("/get-temp")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity retrieveWeather() {
        log.info("WeatherController::retrieveWeather Request received - processing");
        return ResponseEntity.ok(weatherRepository.findTopByOrderByDateDesc());
    }
}

package com.bbynum.tempmon.repositories;

import com.bbynum.tempmon.entities.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, String> {
    Weather findTopByOrderByDateDesc();
}

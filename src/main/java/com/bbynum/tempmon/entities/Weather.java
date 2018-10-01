package com.bbynum.tempmon.entities;

import com.bbynum.tempmon.managers.WeatherManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "temps")
public class Weather {

    @Id
    @Column(name = "datetime")
    private String date;
    @Column(name = "temperature")
    private double TempF;
    @Column(name = "humidity")
    private double Humidity;

    public Weather() {}

    public Weather(double tempF, double humidity) {
        date = new WeatherManager().dateTimeStamp();
        TempF = tempF;
        Humidity = humidity;
    }

    public Weather(String date, double tempF, double humidity) {
        this.date = date;
        TempF = tempF;
        Humidity = humidity;
    }

    public double getTempF() {
        return TempF;
    }

    public void setTempF(double tempF) {
        TempF = tempF;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        Humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date=" + date +
                ", TempF=" + TempF +
                ", Humidity=" + Humidity +
                '}';
    }
}

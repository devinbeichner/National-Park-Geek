package com.techelevator.npgeek.model.JDBCDAO;

import java.util.List;

import com.techelevator.npgeek.model.Weather;

public interface WeatherDAO {
	
	public List<Weather> getWeatherByParkCode(String parkCode);

}

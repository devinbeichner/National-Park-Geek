package com.techelevator.npgeek.model.JDBCDAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Weather;

@Component
public class JDBCWeatherDAO implements WeatherDAO{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		List<Weather> fiveDayForecast = new ArrayList<>();
		
		parkCode = parkCode.toUpperCase();
		
		String sqlGetFiveDays = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet getFiveDays = jdbc.queryForRowSet(sqlGetFiveDays, parkCode);
		
		while (getFiveDays.next()) {
			Weather weather = new Weather();
			weather = mapRowToWeather(getFiveDays);
			fiveDayForecast.add(weather);
		}
		
		return fiveDayForecast;
	}
	
	private Weather mapRowToWeather(SqlRowSet rowSet) {
		
		Weather weather = new Weather();
		
		weather.setParkCode(rowSet.getString("parkcode"));
		weather.setFiveDayForecastValue(rowSet.getInt("fivedayforecastvalue"));
		weather.setLow(rowSet.getInt("low"));
		weather.setHigh(rowSet.getInt("high"));
		
		if(rowSet.getString("forecast").equals("partly cloudy")){
			weather.setForecast("partlyCloudy");
		} else {
			weather.setForecast(rowSet.getString("forecast"));
		}
		
		weather.setAdvisoryMessage();
		
		return weather;
	}

}

package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.JDBCDAO.JDBCWeatherDAO;

import org.junit.Assert;

public class JDBCWeatherDAOTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCWeatherDAO dao;
	private JdbcTemplate jdbc;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		
		dao = new JDBCWeatherDAO(dataSource);
		jdbc = new JdbcTemplate(dataSource);
		
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void get_weather_by_park_code_returns_weather() {
		addPark();
		addWeather();
		
		List<Weather> weathers = dao.getWeatherByParkCode("ZZZ");
		
		String result = "";
		
		for(Weather weather: weathers) {
			if(weather.getParkCode().equals("ZZZ")) {
				result += weather.getForecast() + " ";
			}
		}
		
		Assert.assertEquals("snow partlyCloudy ", result);
	}
	
	private void addWeather() {
		String sqlAddWeather = "INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('ZZZ',1,27,40,'snow')";
		String sqlAddWeather2 = "INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('ZZZ',2,27,40,'partly cloudy')";
		jdbc.update(sqlAddWeather);
		jdbc.update(sqlAddWeather2);
	}
	
	private void addPark() {
		String sqlAddPark = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('ZZZ', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Though a short distance from the urban areas of Cleveland and Akron, Cuyahoga Valley National Park seems worlds away. The park is a refuge for native plants and wildlife, and provides routes of discovery for visitors. The winding Cuyahoga River gives way to deep forests, rolling hills, and open farmlands. Walk or ride the Towpath Trail to follow the historic route of the Ohio & Erie Canal.', 0, 390)";
		jdbc.update(sqlAddPark);
	}
}

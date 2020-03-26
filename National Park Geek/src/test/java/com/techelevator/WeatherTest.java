package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.npgeek.model.Weather;

import org.junit.Assert;

public class WeatherTest {

	Weather weather;
	
	@Before
	public void setup() {
		weather = new Weather();
	}
	
	@Test
	public void advisory_message_prints_for_snow_temp_above_75_and_temp_below_20_with_20_degree_difference() {
		weather.setForecast("snow");
		weather.setHigh(80);
		weather.setLow(18);
		
		weather.setAdvisoryMessage();
		String result = weather.getAdvisoryMessage();
		
		Assert.assertEquals("Pack your snowshoes! It's hot, so be sure to bring an extra gallon of water! It's cold, be sure to wear extra layers and protect yourself from exposure. Be sure to wear breathable layers.", result);
	}
	
	@Test
	public void advisory_message_prints_for_rain_with_high_below_20() {
		weather.setForecast("rain");
		weather.setHigh(19);
		weather.setLow(62);
		
		weather.setAdvisoryMessage();
		String result = weather.getAdvisoryMessage();
		
		Assert.assertEquals("Be sure to pack rain gear and wear waterproof shoes! It's cold, be sure to wear extra layers and protect yourself from exposure. ", result);
	}
	
	@Test
	public void advisory_message_prints_for_thunderstorms_with_low_above_75() {
		weather.setForecast("thunderstorms");
		weather.setHigh(70);
		weather.setLow(87);
		
		weather.setAdvisoryMessage();
		String result = weather.getAdvisoryMessage();
		
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. It's hot, so be sure to bring an extra gallon of water! ", result);
	}
	
	@Test
	public void advisory_message_prints_for_sunny() {
		weather.setForecast("sunny");
		weather.setHigh(70);
		weather.setLow(62);
		
		weather.setAdvisoryMessage();
		String result = weather.getAdvisoryMessage();
		
		Assert.assertEquals("Be sure to bring sunblock! ", result);
	}
	
	@Test
	public void advisory_message_prints_nothing_otherwise() {
		weather.setForecast("meatballs");
		weather.setHigh(70);
		weather.setLow(62);
		
		weather.setAdvisoryMessage();
		String result = weather.getAdvisoryMessage();
		
		Assert.assertEquals("", result);
	}
	
	@Test
	public void fahrenheit_to_celcius_conversion() {
		int c = weather.convertFToC(212);
		
		Assert.assertEquals(100, c);
	}
	
}

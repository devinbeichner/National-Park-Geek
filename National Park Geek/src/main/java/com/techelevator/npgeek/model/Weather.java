package com.techelevator.npgeek.model;

public class Weather {
	
	private String parkCode;
	private Integer fiveDayForecastValue;
	private Integer low;
	private Integer high;
	private String forecast;
	private String advisoryMessage = "";
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public Integer getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(Integer fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public Integer getLow() {
		return low;
	}
	public void setLow(Integer low) {
		this.low = low;
	}
	public Integer getHigh() {
		return high;
	}
	public void setHigh(Integer high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getAdvisoryMessage() {
		return advisoryMessage;
	}
	public void setAdvisoryMessage() {
		
		switch(this.forecast) {
		
			case "snow":
				this.advisoryMessage += "Pack your snowshoes! ";
				break;
				
			case "rain":
				this.advisoryMessage += "Be sure to pack rain gear and wear waterproof shoes! ";
				break;
				
			case "thunderstorms":
				this.advisoryMessage += "Seek shelter and avoid hiking on exposed ridges. ";
				break;
			
			case "sunny":
				this.advisoryMessage += "Be sure to bring sunblock! ";
				break;
				
			default:
				this.advisoryMessage += "";
				break;		
		}
		
		if (this.high > 75 || this.low > 75) {
			this.advisoryMessage += "It's hot, so be sure to bring an extra gallon of water! ";
		}
		
		if (this.high < 20 || this.low < 20) {
			this.advisoryMessage += "It's cold, be sure to wear extra layers and protect yourself from exposure. ";
		}
		
		if (this.high - this.low > 20) {
			this.advisoryMessage += "Be sure to wear breathable layers.";
		}
		
	}
	
	public Integer convertFToC(Integer temperature) {
		Integer celcius;
		
		celcius = (int) Math.round(((temperature - 32) * (5.0/9.0)));
		
		return celcius;
	}
	

}

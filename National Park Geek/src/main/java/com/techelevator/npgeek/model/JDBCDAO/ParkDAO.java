package com.techelevator.npgeek.model.JDBCDAO;

import java.util.List;
import java.util.Map;

import com.techelevator.npgeek.model.Park;

public interface ParkDAO {
	
	public List<Park> getAllParks();
	
	public Park getParkByCode(String parkCode);
	
	public List<Park> getFavoriteParks();

}

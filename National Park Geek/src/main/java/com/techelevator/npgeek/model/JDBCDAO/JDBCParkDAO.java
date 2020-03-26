package com.techelevator.npgeek.model.JDBCDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
@Component
public class JDBCParkDAO implements ParkDAO{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public List<Park> getAllParks(){
		
		List<Park> allParks = new ArrayList<>();
		
		String sqlGetAllParks = "SELECT * FROM park";
		SqlRowSet getAllParks = jdbc.queryForRowSet(sqlGetAllParks);
		
		while(getAllParks.next()) {
			Park park = mapRowToPark(getAllParks);
			allParks.add(park);
		}
		
		return allParks;
	}
	
	public Park getParkByCode(String parkCode) {
		
		String sqlGetParkCode = "SELECT * FROM park WHERE parkcode ILIKE ?";
		SqlRowSet getParkCode = jdbc.queryForRowSet(sqlGetParkCode, parkCode);
		
		getParkCode.next();
			Park park = mapRowToPark(getParkCode);
			
		return park;
	}
	
	public List<Park> getFavoriteParks(){
		List<Park> favoriteParks = new ArrayList<>();
		String sqlGetReviewedParks = "SELECT park.parkname, park.parkcode, COUNT (*) FROM park " + 
									"JOIN survey_result ON survey_result.parkcode = park.parkcode " + 
									"GROUP BY park.parkname, park.parkcode ORDER BY count DESC, park.parkname";
		SqlRowSet results = jdbc.queryForRowSet(sqlGetReviewedParks);
		while(results.next()) {
			Park surveyPark = new Park();
			surveyPark.setParkName(results.getString(1));
			surveyPark.setParkCode(results.getString(2).toLowerCase());
			surveyPark.setSurveyQty(results.getInt(3));
			favoriteParks.add(surveyPark);
		}
		
		return favoriteParks;
	}
	
	private Park mapRowToPark(SqlRowSet rowSet) {
		Park park = new Park();
		
		park.setParkCode(rowSet.getString("parkcode").toLowerCase());
		park.setParkName(rowSet.getString("parkname"));
		park.setState(rowSet.getString("state"));
		park.setAcreage(rowSet.getInt("acreage"));
		park.setElevationInFeet(rowSet.getInt("elevationinfeet"));
		
		BigDecimal milesOfTrail = new BigDecimal(rowSet.getFloat("milesoftrail"));
		park.setMilesOfTrail(milesOfTrail);
		
		park.setNumberOfCampsites(rowSet.getInt("numberofcampsites"));
		park.setClimate(rowSet.getString("climate"));
		park.setYearFounded(rowSet.getInt("yearfounded"));
		park.setAnnualVisitorCount(rowSet.getInt("annualvisitorcount"));
		park.setInspirationalQuote(rowSet.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(rowSet.getString("inspirationalquotesource"));
		park.setParkDescription(rowSet.getString("parkdescription"));
		park.setEntryFee(rowSet.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(rowSet.getInt("numberofanimalspecies"));
		
		return park;
	}

}

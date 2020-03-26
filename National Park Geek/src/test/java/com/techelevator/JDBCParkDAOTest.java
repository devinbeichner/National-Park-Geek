package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.JDBCDAO.JDBCParkDAO;

public class JDBCParkDAOTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCParkDAO dao;
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
		
		dao = new JDBCParkDAO(dataSource);
		jdbc = new JdbcTemplate(dataSource);
		
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void get_all_parks_returns_all_parks() {
		
		String getCountOfAllParks = "SELECT COUNT(*) AS count FROM park";
		SqlRowSet result1 = jdbc.queryForRowSet(getCountOfAllParks);
		result1.next();
		Integer expected1 = result1.getInt(1);
		
		List<Park> actual1 = dao.getAllParks();
		Integer actual1Size = actual1.size();
		
		Assert.assertEquals(expected1, actual1Size);
		
	}
	
	@Test
	public void get_park_by_code_returns_park() {
		
		Park park = dao.getParkByCode("CVNP");
		String parkName = park.getParkName();
		
		Assert.assertEquals("Cuyahoga Valley National Park", parkName);
		
	}
	
	@Test
	public void get_favorite_parks_returns_correct_amount() {
		
		addPark();
		
		String sqlAddSurvey = "INSERT INTO survey_result "
							  + "VALUES (DEFAULT, 'ZZZ', 'dev@dev.dev', 'PA', 'sedentary')";
		jdbc.update(sqlAddSurvey);
		
		List<Park> parks = dao.getFavoriteParks();
		
		System.out.println(parks.size());
		
		int result = 0;
		
		for (Park park : parks) {
			if (park.getParkCode().equals("zzz")) {
				result++;
			}
		}
		
		Assert.assertEquals(1, result);
	}
	
	private void addPark() {
		String sqlAddPark = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('ZZZ', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Though a short distance from the urban areas of Cleveland and Akron, Cuyahoga Valley National Park seems worlds away. The park is a refuge for native plants and wildlife, and provides routes of discovery for visitors. The winding Cuyahoga River gives way to deep forests, rolling hills, and open farmlands. Walk or ride the Towpath Trail to follow the historic route of the Ohio & Erie Canal.', 0, 390)";
		jdbc.update(sqlAddPark);
	}
	
	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	public DataSource getDataSource() {
		return dataSource;
	}
}

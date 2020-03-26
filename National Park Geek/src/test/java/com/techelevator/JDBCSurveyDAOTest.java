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
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.JDBCDAO.JDBCSurveyDAO;
import com.techelevator.npgeek.model.JDBCDAO.JDBCWeatherDAO;

import org.junit.Assert;

public class JDBCSurveyDAOTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCSurveyDAO dao;
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
		
		dao = new JDBCSurveyDAO(dataSource);
		jdbc = new JdbcTemplate(dataSource);
		
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test_survey_save() {
		String sqlAddApark = "INSERT INTO park VALUES ('TESTCODE', 'TESTNAME', 'TESTSTATE', 1,1,1,1,'TESTCLIMIATE', 9999, 1, 'TESTQUOTE', 'TESTQUOTED','TESTDESC', 99, 999)";
		jdbc.update(sqlAddApark);
		Survey testSurvey = new Survey();
		testSurvey.setParkCode("TESTCODE");
		testSurvey.setEmailAddress("test@test.com");
		testSurvey.setState("TESTAS");
		testSurvey.setActivityLevel("TESTACTIVITY");
		dao.saveSurvey(testSurvey);
		SqlRowSet results = jdbc.queryForRowSet("SELECT * FROM survey_result WHERE parkcode = 'TESTCODE'");
		results.next();
		Long surveyId = results.getLong(1);
		Assert.assertNotNull(surveyId);
		
	}
	
	
}
package com.techelevator.npgeek.model.JDBCDAO;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Survey;

@Component
public class JDBCSurveyDAO implements SurveyDAO{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public Survey saveSurvey(Survey newSurvey) {
		Survey survey = new Survey();
		String sqlSaveSurvey = "INSERT INTO survey_result VALUES (DEFAULT, ?, ?, ?, ?, ?)";
		
		jdbc.update(sqlSaveSurvey, newSurvey.getParkCode(), newSurvey.getEmailAddress(), newSurvey.getState(), newSurvey.getActivityLevel(), LocalDate.now());
		
		return survey;
	}

}

//String parkCode, String emailAddress, String state, String activityLevel)
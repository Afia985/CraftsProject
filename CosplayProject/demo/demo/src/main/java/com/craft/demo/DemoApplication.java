package com.craft.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.craft.demo.DAO.AnimeDAO;
import com.craft.demo.DAO.EventDAO;
import com.craft.demo.entities.Anime;
import com.craft.demo.entities.event;
import com.craft.demo.test.AnimeTest;
import com.craft.demo.test.EventTest;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		/*//Creates test class
		EventTest eventTest = new EventTest();
		
		
		//Test getting all of the events
		eventTest.testGetAll();
		
		//Test creating an event
		eventTest.testCreateOne();
		
		//Test searching by name
		eventTest.testSearchByName();
		
		//Test searching by Start and End Date
		eventTest.testSearchByStartAndEndDate();
		
		//Test search by Start date
		eventTest.testSearchByStartDate();
		
		//Test search by Start date
		eventTest.testSearchByEndDate();*/
		
		/*AnimeTest animeTest = new AnimeTest();
		
		//Test getting all anime
		animeTest.testGetAllAnime();
		
		//test creating anime
		animeTest.testCreateNewAnime();
		
		//Test searching by Name, Season, Year
		animeTest.testSearchByNameSeasonYear();
		
		//Test searching by Name
		animeTest.testSearchByName();
		
		//Test searching by Season
		animeTest.testSearchBySeason();	
		
		//Test searching by Year
		animeTest.testSearchByYear();*/
	}

}

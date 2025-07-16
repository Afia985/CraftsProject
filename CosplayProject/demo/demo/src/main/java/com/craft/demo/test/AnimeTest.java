package com.craft.demo.test;

import java.util.ArrayList;
import java.util.List;

import com.craft.demo.DAO.AnimeDAO;
import com.craft.demo.entities.Anime;

public class AnimeTest {

	private AnimeDAO animeDAO = new AnimeDAO();
	
	public void testGetAllAnime() {
		
		System.out.println("******************Get All Anime*************************");
		
		List <Anime> animes = new ArrayList<>();
		animes = animeDAO.getAll();
		
		animes.forEach(System.out::println);
	}
	
	public void testCreateNewAnime() {
	
		System.out.println("******************Create New Anime*************************");
		
		List <Anime> newanimes = new ArrayList<>();
		Anime anime = new Anime ("Black Butler");
		anime.setNextSeasonYear(2025);
		anime.setNextSeasonTime("Spring");
		animeDAO.create(anime);
		newanimes = animeDAO.getAll();
		
		newanimes.forEach(System.out::println);
	}
	
	public void testSearchByNameSeasonYear() {
		System.out.println("******************Search name, season, year*************************");
		List<Anime> animeByAll = new ArrayList<>();
		animeByAll = animeDAO.getByMultipleString("Black Butler", "=" , "Spring", "=", 2025);
		animeByAll.forEach(System.out::println);
	}
	
	public void testSearchByName() {
		System.out.println("******************Search name*************************");
		List<Anime> animeByName = new ArrayList<>();
		animeByName = animeDAO.getByMultipleString("Fire Force", "=", "", "=", 0);
		animeByName.forEach(System.out::println);
		
	}
	
	public void testSearchBySeason() {
		System.out.println("******************Search season*************************");
		List<Anime> animeBySeason = new ArrayList<>();
		animeBySeason = animeDAO.getByMultipleString(null, "=", "Spring", "=", 0);
		animeBySeason.forEach(System.out::println);
	}
	
	public void testSearchByYear() {
		System.out.println("******************Search year*************************");
		List<Anime> animeByYear = new ArrayList<>();
		animeByYear = animeDAO.getByMultipleString(null, "=", null, "=", 2026);
		animeByYear.forEach(System.out::println);
	}

	
}

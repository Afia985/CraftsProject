package com.craft.demo.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.craft.demo.DAO.EventDAO;
import com.craft.demo.entities.event;

public class EventTest {
	
	private EventDAO eventDao = new EventDAO();
	
	public void testGetAll() {
		System.out.println("******************Get All*************************");
		List<event> events = new ArrayList<>();
		events = eventDao.getAll();
		events.forEach(System.out::println);
	}
	
	public void testCreateOne() {
		System.out.println("******************Create*************************");
		List<event> events = new ArrayList<>();
		event newEvent = new event("MomoCon 2026");
		
		newEvent.setBought(true);
		newEvent.setEventUUID(UUID.randomUUID());
		newEvent.setStartDate(LocalDate.parse("2026-05-21"));
		newEvent.setEndDate(LocalDate.parse("2026-05-24"));
		newEvent.setLocation("Atlanta, GA");
		newEvent.setCost(100);
		
		eventDao.create(newEvent);
		
		events = eventDao.getAll();
		
		events.forEach(System.out::println);
		
		System.out.println("******************Get By Name*************************");
	}

	public void testSearchByName() {
		List<event> eventsByName = new ArrayList<>();
		eventsByName = eventDao.getByName("MomoCon 2025");
		eventsByName.forEach(System.out::println);
	}
	
	public void testSearchByStartAndEndDate()
	{
		System.out.println("******************Search By Start and End Date*************************");
		List<event> eventsByStartEnd = new ArrayList<>();
		eventsByStartEnd = eventDao.getByDate(LocalDate.parse("2025-12-18"), LocalDate.parse("2025-12-21"), "=", ">=");
		eventsByStartEnd.forEach(System.out::println);
	}
	
	public void testSearchByStartDate() {
		System.out.println("******************Search by Start Date*************************");
		
		List<event> eventsByStart = new ArrayList<>();
		eventsByStart = eventDao.getByDate(LocalDate.parse("2025-05-20"), null, "<=", "=");
		eventsByStart.forEach(System.out::println);
	}
	
	public void testSearchByEndDate() {
		System.out.println("******************Search by End Date*************************");
		
		List<event> eventsByEnd = new ArrayList<>();
		eventsByEnd = eventDao.getByDate(null, LocalDate.parse("2026-05-24"), "=", "<=");
		eventsByEnd.forEach(System.out::println);
	}
	
}

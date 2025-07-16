package com.craft.demo.entities;

import java.time.LocalDate;
import java.util.UUID;


//Public class for events. Can be access by all classes in this program.
//Syntax: {Public/private} {Type} {name of the object}
public class event {
	
	
	private UUID Id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String eventname;
	private String location;
	private boolean bought = false;
	private float cost = 0;
	
	public event (String name) {
		setEventname(name);
	}
	
	public event () {
	}
	
	public boolean getBought() {
		return this.bought;
	}
	
	public void setEventUUID (UUID eventUUID) {
		this.Id = eventUUID;
	}

	@Override
	public String toString() {
		return "event [Id=" + Id + ", startDate=" + startDate + ", endDate=" + endDate + ", eventname=" + eventname
				+ ", location=" + location + ", bought=" + bought + ", cost=" + cost + "]";
	}

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}
	
	
		
}

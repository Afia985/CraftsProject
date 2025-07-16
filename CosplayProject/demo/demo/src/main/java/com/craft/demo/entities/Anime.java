package com.craft.demo.entities;

import java.util.UUID;

public class Anime {
	private UUID animeID;
	private String title;
	private String nextSeasonTime;
	private int nextSeasonYear;
	
	
	//Constructors, methods/function, parameters
	//Constructors - What code is going to run when first create that object.
	public Anime (String title) {
		setTitle(title);
	}
	
	public Anime () {
	}
	
	/*Methods/Function - a set of code that run when called. It will return something or nothing.
	 syntax {Public/private} {Data type/void}
	 * void - return nothing, Data type - must return something of that data type.
	 * Parameter - they passed into the method. When calling a method, the parameter must be of the type you said in the method.
	 */
	public void setNextSeasonYear(int Year) {
		this.nextSeasonYear = Year;
	}
	
	public int getNextSeasonYear() {
		return this.nextSeasonYear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UUID getAnimeID() {
		return animeID;
	}

	public void setAnimeID(UUID animeID) {
		this.animeID = animeID;
	}

	public String getNextSeasonTime() {
		return nextSeasonTime;
	}

	public void setNextSeasonTime(String nextSeasonTime) {
		this.nextSeasonTime = nextSeasonTime;
	}

	@Override
	public String toString() {
		return "Anime [animeID=" + animeID + ", title=" + title + ", nextSeasonTime=" + nextSeasonTime
				+ ", nextSeasonYear=" + nextSeasonYear + "]";
	}
	
	
	

}

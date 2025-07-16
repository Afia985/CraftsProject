package com.craft.demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import com.craft.demo.entities.DatabaseUtils;
import com.craft.demo.entities.event;

public class EventDAO implements DAO <event, UUID>{

	private static final Logger LOGGER = Logger.getLogger(EventDAO.class.getName());
	
	private static final String GET_All = "select eventid, eventstartdate, eventenddate, eventlocation, eventname, ticketbought, ticketcost from eventprograms";
	private static final String GET_BY_NAME = "select eventid, eventstartdate, eventenddate, eventlocation, eventname, ticketbought, ticketcost from eventprograms where eventname = ?";
	private static final String GET_BY_ATTRIBUTES = "select eventid, eventstartdate, eventenddate, eventlocation, eventname, ticketbought, ticketcost from eventprograms where";
	
	
	private static final String CREATE = "insert into eventprograms (eventid, eventstartdate, eventenddate, eventname, eventlocation, ticketbought, ticketcost) values (?, ?, ?, ?, ?, ?, ?)";
	
	
	@Override
	public List<event> getAll() {
		List<event> events = new ArrayList<>();
		Connection connection = DatabaseUtils.getConnection();
		try (Statement statement = connection.createStatement()){
			ResultSet rs = statement.executeQuery(GET_All);
			events = this.processResultSet(rs);
		} catch (SQLException e) {
			DatabaseUtils.handleSqlException(GET_All, e, LOGGER);
		}
		return events;
	}

	@Override
	public event create(event entity){
		UUID eventID = UUID.randomUUID();
		Connection connection = DatabaseUtils.getConnection();
		
		try {
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(CREATE);
			statement.setObject(1, eventID);
			statement.setObject(2, entity.getStartDate());
			statement.setObject(3, entity.getEndDate());
			statement.setString(4, entity.getEventname());
			statement.setString(5, entity.getLocation());
			statement.setBoolean(6, entity.getBought());
			statement.setFloat(7, entity.getCost());
			statement.execute();
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException c) {
				DatabaseUtils.handleSqlException("EventDAO.create.rollback", c, LOGGER);
			}
			DatabaseUtils.handleSqlException("EventDAO.create", e, LOGGER);
		}
		Optional<event> event = this.getOne(eventID);
		if (!event.isPresent()){
			return null;
		}
		return event.get();
	}

	@Override
	public Optional<event> getOne(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	public List<event> getByName(String name){
		try (PreparedStatement statement = DatabaseUtils.getConnection().prepareStatement(GET_BY_NAME)){
			statement.setObject(1,name);
			ResultSet rs = statement.executeQuery();
			List<event> events = this.processResultSet(rs);
			if (!events.isEmpty()) {
				return events;
			}
		} catch (SQLException e) {
			DatabaseUtils.handleSqlException("EventDAO.getByName", e, LOGGER);
		}
		return null;
	}
	
	public List<event> getByDate (LocalDate startDate, LocalDate endDate, String startOperator, String EndOperator){
		int col = 1;
		String sqlQuery = GET_BY_ATTRIBUTES;
		Map <LocalDate, Integer> attribute = new HashMap<>();
		if (startDate != null) {
			sqlQuery = addSearch_Startdate(sqlQuery, col == 1, startOperator);
			attribute.put(startDate, col);
			col++;
		}
		
		if (endDate != null) {
			sqlQuery = addSearch_Enddate(sqlQuery, col == 1, EndOperator);
			attribute.put(endDate, col);
			col++;
		}
		
		try (PreparedStatement statement = DatabaseUtils.getConnection().prepareStatement(sqlQuery)){
			for (Map.Entry <LocalDate, Integer> entry : attribute.entrySet()) {
				statement.setObject(entry.getValue(),entry.getKey());
			}
			
			ResultSet rs = statement.executeQuery();
			List<event> events = this.processResultSet(rs);
			if (!events.isEmpty()) {
				return events;
			}
		} catch (SQLException e) {
			DatabaseUtils.handleSqlException("EventDAO.getByAttributes", e, LOGGER);
		}
		return null;
	}

	@Override
 	public event updates(event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}
	
		
	private List<event> processResultSet(ResultSet rs) throws SQLException {
	    List<event> events = new ArrayList<>();
	    while (rs.next()) {
	    	event event = new event();
	    	event.setId(UUID.fromString(rs.getString("eventid")));
	    	event.setEventname(rs.getString("eventname"));
	    	event.setStartDate(LocalDate.parse(rs.getString("eventstartdate")));
	    	event.setEndDate(LocalDate.parse(rs.getString("eventenddate")));
	    	event.setLocation(rs.getString("eventlocation"));
	    	event.setBought(rs.getBoolean("ticketbought"));
	    	event.setCost(rs.getFloat("ticketcost"));
	    	events.add(event);
	    }
	    return events;
	  }
	
/*
 * 	private UUID Id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String eventname;
	private String location;
	private boolean bought = false;
	private float cost = 0;
 */
	
	private String addSearch_Startdate(String querySql, boolean first, String operator) {
		if (!first)
			return querySql + " and eventstartdate " + operator + " ?";
		return querySql + " eventstartdate " + operator + " ?";
	}
	
	private String addSearch_Enddate(String querySql, boolean first, String operator) {
		if (!first)
			return querySql + " and eventenddate " + operator + " ?";
		return querySql + " eventenddate " + operator + " ?";
	}
	
	private String addSearch_Location(String querySql, boolean first, String operator) {
		if (!first)
			return querySql + " and eventlocation " + operator + " ?";
		return querySql + " eventlocation " + operator + " ?";
	}
	
	private String addSearch_Bought(String querySql, boolean first, String operator) {
		if (!first)
			return querySql + " and ticketbought " + operator + " ?";
		return querySql + " ticketbought " + operator + " ?";
	}
	
	private String addSearch_Cost(String querySql, boolean first, String operator) {
		if (first)
			return querySql + " and ticketcost " + operator + " ?";
		return querySql + " ticketcost " + operator + " ?";
	}
		

}

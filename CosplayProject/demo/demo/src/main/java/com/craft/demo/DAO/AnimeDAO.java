package com.craft.demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import com.craft.demo.entities.Anime;
import com.craft.demo.entities.DatabaseUtils;
import com.craft.demo.entities.event;

public class AnimeDAO implements DAO <Anime, UUID>{
	
	private static final Logger LOGGER = Logger.getLogger(AnimeDAO.class.getName());
	
	//variable to hold the name of the anime name column
	private static final String ANIMENAME_DB = "animename";
	
	//Variable to hold the name of the anime season column
	private static final String ANIMESEASON_DB = "animeseason";
	
	//Variable to hold the name of the anime year column
	private static final String ANIMEYEAR_DB = "animeyear";
	
	private static final String GET_ALL = "Select animeid, " + ANIMENAME_DB + ", " + ANIMESEASON_DB + ", " + ANIMEYEAR_DB + " from anime";
	private static final String GET_BY_ATTRIBUTES = "Select animeid, animename, animeseason, animeyear from anime where ";
	
	private static final String CREATE = "Insert into anime (animeid, animename, animeseason, animeyear) values (?, ?, ?, ?)";

	@Override
	public List<Anime> getAll() {
		Connection connection = DatabaseUtils.getConnection();
		List <Anime> animes = new ArrayList<>();
		
		try (Statement statement = connection.createStatement()){
			ResultSet rs = statement.executeQuery(GET_ALL);
			animes = this.processResultSet(rs);
			
		} catch (SQLException e)
		{
			DatabaseUtils.handleSqlException(GET_ALL, e, LOGGER);
		}
		return animes;
	}

	@Override
	public Anime create(Anime anime) {
		Connection connection = DatabaseUtils.getConnection();
		UUID uuid = UUID.randomUUID();
		try
		{
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(CREATE);
			statement.setObject(1, uuid);
			statement.setString(2, anime.getTitle());
			statement.setString(3, anime.getNextSeasonTime());
			statement.setInt(4, anime.getNextSeasonYear());
			
			statement.execute();
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException c) {
				DatabaseUtils.handleSqlException("AnimeDAO.create.rollback", c, LOGGER);
			}
			DatabaseUtils.handleSqlException("AnimeDAO.create", e, LOGGER);
		}
		Optional<Anime> animes = this.getOne(uuid);
		if (!animes.isPresent()){
			return null;
		}
		return animes.get();
	}

	@Override
	public Optional<Anime> getOne(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	public List<Anime> getByMultipleString(String animename, String nameOperator, String animeseason, String seasonOperator, int animeyear){		
		int col = 1;
		String sqlQuery = GET_BY_ATTRIBUTES;
		Map <String, Integer> attribute = new HashMap<>();
		
		if (animename != null && animename.trim() != "") {
			sqlQuery = addSearch_Attribute(sqlQuery, ANIMENAME_DB, col == 1, nameOperator);
			attribute.put(animename, col);
			col++;
		}
		
		if (animeseason != null && animeseason.trim() != "") {
			sqlQuery = addSearch_Attribute(sqlQuery, ANIMESEASON_DB, col == 1, seasonOperator);
			attribute.put(animeseason, col);
			col++;
		}
		
		if (!(animeyear == 0)) {
			sqlQuery = sqlQuery = addSearch_Attribute(sqlQuery, ANIMEYEAR_DB, col == 1, "=");
		}
		
		try (PreparedStatement statement = DatabaseUtils.getConnection().prepareStatement(sqlQuery)){
			for (Map.Entry <String, Integer> entry : attribute.entrySet()) {
				statement.setObject(entry.getValue(),entry.getKey());
			}
			
			if (!(animeyear == 0)) {
				statement.setObject(col, animeyear);
			}
			
			ResultSet rs = statement.executeQuery();
			List<Anime> animes = this.processResultSet(rs);
			if (!animes.isEmpty()) {
				return animes;
			}
		} catch (SQLException e) {
			DatabaseUtils.handleSqlException("AnimeDAO.getByMultipleString", e, LOGGER);
		}
		return null;
	}

	@Override
	public Anime updates(Anime entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}
	
	private List<Anime> processResultSet(ResultSet rs) throws SQLException {
	    List<Anime> animes = new ArrayList<>();
	    while (rs.next()) {
	    	Anime anime = new Anime();
	    	anime.setAnimeID(UUID.fromString(rs.getString("animeid")));
	    	anime.setTitle(rs.getString("animename"));
	    	anime.setNextSeasonTime(rs.getString("animeseason"));
	    	anime.setNextSeasonYear(rs.getInt("animeyear"));
	    	animes.add(anime);
	    }
	    return animes;
	  }

	private String addSearch_Attribute (String querySql, String attribute, boolean first, String operator) {
		if (!first)
			return querySql + " and " + attribute + ' ' + operator + " ?";
		return querySql + attribute + ' ' + operator + " ?";
	}
}

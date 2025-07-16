package com.craft.demo.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseUtils {

	private static final String URl = "jdbc:postgresql://localhost:5432/Craft";
	private static final String USERNAME = "postgres";
	  private static final String PASSWORD = "postgres";
	  private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());
	  private static final String exceptionFormat = "exception in %s, message: %s, code: %s";
	  private static Connection connection;

	  public static Connection getConnection() {
	    if (connection == null) {
	      synchronized (DatabaseUtils.class) {
	        if (connection == null) {
	          try {
	            connection = DriverManager.getConnection(URl, USERNAME, PASSWORD);
	          } catch (SQLException e) {
	            handleSqlException("DatabaseUtils.getConnection", e, LOGGER);
	          }
	        }
	      }
	    }
	    return connection;
	  }

	  public static void handleSqlException(String method, SQLException e, Logger log) {
	    log.warning(String.format(exceptionFormat, method, e.getMessage(), e.getErrorCode()));
	    throw new RuntimeException(e);
	  }
}

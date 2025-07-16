package com.craft.demo.DAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface DAO <T, Id extends UUID>{
	List<T> getAll();
	T create (T entity);
	Optional<T> getOne (Id id);
	T updates (T entity);
	void delete (Id id);
	

}

package it.hellokitty.gt.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ServiceExt<T> {
	/**
	 * This implementation returns a single element with id = 'id' parameter 
	 * or null if there's no element with that id.
	 * 
	 * @param id - Object
	 * @throws Exception
	 */
	T fetchById(Object id) throws IllegalArgumentException, Exception;
	
	List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn) throws IllegalArgumentException, Exception;
		
	Long count() throws IllegalArgumentException, Exception;
	
	Long count(HashMap<String,Object> paramEquals, HashMap<String,Object> paramLike, HashMap<String,Object> paramGE, HashMap<String,Object> paramLE ) throws IllegalArgumentException, Exception;
	
	List<T> search(Integer start, Integer limit, 
			LinkedHashMap<String,String> orderColumn,
			HashMap<String,Object> paramEquals,
			HashMap<String,Object> paramLike,
			HashMap<String,Object> paramGE,
			HashMap<String,Object> paramLE);
}

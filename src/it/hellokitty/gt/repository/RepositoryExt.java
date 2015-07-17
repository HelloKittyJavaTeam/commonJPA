package it.hellokitty.gt.repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface RepositoryExt<T> {
	/**
	 * This implementation returns a single element with id = 'id' parameter 
	 * or null if there's no element with that id.
	 * 
	 * @param id - Object
	 * @throws Exception
	 */
	T getById(Object id) throws  Exception;
	
	List<T> getAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn) throws Exception;
		
	Long count() throws Exception;
	
	Long count(HashMap<String,Object> paramEquals, HashMap<String,Object> paramLike, HashMap<String,Object> paramGE, HashMap<String,Object> paramLE ) throws Exception;
	
	List<T> search(Integer start, Integer limit, 
			LinkedHashMap<String,String> orderColumn,
			HashMap<String,Object> paramEquals,
			HashMap<String,Object> paramLike,
			HashMap<String,Object> paramGE,
			HashMap<String,Object> paramLE);
}

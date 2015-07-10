package it.hellokitty.gt.service;

import it.hellokitty.gt.entity.BaseObject;

import java.util.LinkedHashMap;
import java.util.List;

public interface Service<T> {
	
	List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn, String user) throws Exception;
	
//	Long count(HashMap<String,Object> paramEquals, HashMap<String,Object> paramLike, HashMap<String,Object> paramGE, HashMap<String,Object> paramLE ) throws Exception;
	
	void insert(BaseObject elem, String user) throws IllegalArgumentException, Exception;

	void delete(BaseObject elem, String user) throws IllegalArgumentException, Exception;
	
	BaseObject merge (BaseObject elem, String user) throws Exception;
}

package it.hellokitty.gt.service;

import it.hellokitty.gt.entity.BaseObject;

import java.util.LinkedHashMap;
import java.util.List;

public interface Service<T> extends ServiceExt<T>{
	
	List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn, String user) throws IllegalArgumentException, Exception;
		
	void insert(BaseObject elem, String user) throws IllegalArgumentException, Exception;

	void delete(BaseObject elem, String user) throws IllegalArgumentException, Exception;
	
	BaseObject merge (BaseObject elem, String user) throws IllegalArgumentException, Exception;
}

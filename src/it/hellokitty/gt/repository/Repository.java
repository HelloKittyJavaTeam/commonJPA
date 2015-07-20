package it.hellokitty.gt.repository;

import it.hellokitty.gt.entity.BaseObject;

import java.util.LinkedHashMap;
import java.util.List;


public interface Repository<T> extends RepositoryExt<T>{
	
	void insert(BaseObject elem, String user) throws  Exception;

	void delete(BaseObject elem, String user) throws Exception;
	
	BaseObject merge (BaseObject elem, String user) throws Exception;
}

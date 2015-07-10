package it.hellokitty.gt.repository.impl;

import it.hellokitty.gt.entity.BaseObject;
import it.hellokitty.gt.repository.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityTransaction;

public class RepositoryImpl<T> extends RepositoryImplExt<T> implements Repository<T> {
	@Override
	public List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn, String user) throws Exception{
		HashMap<String,Object> paramEquals = new HashMap<String, Object>();
		paramEquals.put("userCreated", user);
		paramEquals.put("active", true);
		HashMap<String,Object> paramLike = new HashMap<String, Object>();
        HashMap<String,Object> paramGE = new HashMap<String, Object>();
        HashMap<String,Object> paramLE = new HashMap<String, Object>();
        
        return search(start, limit, orderColumn, paramEquals, paramLike, paramGE, paramLE);
	}
	
	
	
	@Override
	public void insert(BaseObject elem, String user) throws Exception{
		EntityTransaction transaction;

		elem.setUserCreated(user);
		elem.setCreateDate(new Date());
		elem.setActive(true);
		
		transaction = getEm().getTransaction();
		try {
			transaction.begin();
		    getEm().persist(elem);
		    transaction.commit(); 
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
		    getEm().clear(); // Clears all the entities from the EntityManager
		}
	}

	@Override
	public void delete(BaseObject elem, String user) throws Exception{
		EntityTransaction transaction = null;
		try {
			transaction = getEm().getTransaction();
			transaction.begin();
		    
			elem.setUserUpdate(user);
			elem.setUpdate(new Date());
			elem.setActive(false);
				
			getEm().merge(elem);

		    transaction.commit();
		} catch (Exception e) {
		   	transaction.rollback();
		   	throw e;
		} finally {
			getEm().clear(); // Clears all the entities from the EntityManager
		}
	}

	@Override
	public BaseObject merge(BaseObject elem, String user) throws Exception {
		BaseObject result = null;

		EntityTransaction transaction= null;
		try {
			transaction = getEm().getTransaction();
			transaction.begin();
			
			elem.setUserUpdate(user);
			elem.setUpdate(new Date());
		    result = getEm().merge(elem);
		    
		    transaction.commit();
		} catch (Exception e) {
		    transaction.rollback();
		    throw e;
		} finally {
			getEm().clear(); // Clears all the entities from the EntityManager
		}
		return result;
	}
}

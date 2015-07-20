package it.hellokitty.gt.repository.utils;

import it.hellokitty.gt.entity.BaseObject;
import it.hellokitty.gt.repository.Repository;

import java.util.Date;

import javax.persistence.EntityTransaction;

public abstract class RepositoryUtils<T> extends RepositoryUtilsExt<T> implements Repository<T>{
	
	public RepositoryUtils(){}
	
	@Override
	public void insert(BaseObject elem, String user) throws Exception{
		EntityTransaction transaction;

		elem.setUserCreate(user);
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
			elem.setUpdateDate(new Date());
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
			elem.setUpdateDate(new Date());
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

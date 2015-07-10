package it.hellokitty.gt.service.impl;

import it.hellokitty.gt.entity.BaseObject;
import it.hellokitty.gt.repository.Repository;
import it.hellokitty.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.service.Service;

import java.util.LinkedHashMap;
import java.util.List;

public class ServiceImpl<T> extends ServiceImplExt<T> implements Service<T> {
	protected Repository<T> repImpl = new RepositoryImpl<T>();

	@Override
	public List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn, String user) throws IllegalArgumentException, Exception {
		if(start == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be null.");
		}
		
		if(start < 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0.");
		}
		
		if(limit == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be null.");
		}
		
		if(orderColumn == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - orderColumn parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.trim().equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
		}
		
		return repImpl.fetchAll(start, limit, orderColumn, user);
	}

	@Override
	public void insert(BaseObject elem, String user) throws IllegalArgumentException, Exception {
		if(elem == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.trim().equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
		}
		
		repImpl.insert(elem, user);
	}

	@Override
	public void delete(BaseObject elem, String user) throws IllegalArgumentException, Exception {
		if(elem == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.trim().equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
		}
		
		repImpl.delete(elem, user);
	}

	@Override
	public BaseObject merge(BaseObject elem, String user) throws Exception {
		if(elem == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.trim().equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
		}
		
		return repImpl.merge(elem, user);
	}
}

package it.hellokitty.gt.service.impl;

import it.hellokitty.gt.repository.RepositoryExt;
import it.hellokitty.gt.repository.impl.RepositoryImplExt;
import it.hellokitty.gt.service.ServiceExt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ServiceImplExt<T> implements ServiceExt<T> {
	protected RepositoryExt<T> repImplExt = new RepositoryImplExt<T>();

	@Override
	public T fetchById(Object id) throws IllegalArgumentException, Exception {
		if(id == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be null.");
		}

		return repImplExt.fetchById(id);
	}

	@Override
	public List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn) throws IllegalArgumentException, Exception {
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
		
		return repImplExt.fetchAll(start, limit, orderColumn);
	}

	@Override
	public Long count() throws Exception {
		return repImplExt.count();
	}

	@Override
	public Long count(HashMap<String, Object> paramEquals,
			HashMap<String, Object> paramLike, HashMap<String, Object> paramGE,
			HashMap<String, Object> paramLE) throws Exception {
		if(paramEquals == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramEquals parameter can't be null.");
		}
		
		if(paramLike == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramLike parameter can't be null.");
		}
		
		if(paramGE == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramGE parameter can't be null.");
		}
		
		if(paramLE == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramLE parameter can't be null.");
		}
		
		return repImplExt.count(paramEquals, paramLike, paramGE, paramLE);
	}

	@Override
	public List<T> search(Integer start, Integer limit,
			LinkedHashMap<String, String> orderColumn,
			HashMap<String, Object> paramEquals,
			HashMap<String, Object> paramLike, HashMap<String, Object> paramGE,
			HashMap<String, Object> paramLE) {
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
		
		if(paramEquals == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramEquals parameter can't be null.");
		}
		
		if(paramLike == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramLike parameter can't be null.");
		}
		
		if(paramGE == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramGE parameter can't be null.");
		}
		
		if(paramLE == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - paramLE parameter can't be null.");
		}
		
		return repImplExt.search(start, limit, orderColumn, paramEquals, paramLike, paramGE, paramLE);
	}

}

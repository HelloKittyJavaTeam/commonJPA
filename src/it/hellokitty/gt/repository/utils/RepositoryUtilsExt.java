package it.hellokitty.gt.repository.utils;

import it.hellokitty.gt.repository.RepositoryExt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public abstract class RepositoryUtilsExt<T> implements RepositoryExt<T>{
	public  Class<T> typeParameterClass;
	public static String persistenceUnitName;
	private static Map<String, EntityManager> emMap = new HashMap<String, EntityManager>();
	
	public RepositoryUtilsExt(){
		super();
	}

	public static EntityManager getEm(){
		if(!emMap.containsKey(persistenceUnitName)){
			emMap.put(persistenceUnitName, Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager());
		}
		return emMap.get(persistenceUnitName);
	}


	public static boolean isAdmin(String user){
		return user.equals("admin");
	}

	public TypedQuery<T> paginate(CriteriaQuery<T> cq, Integer start, Integer limit) throws IllegalArgumentException {

		TypedQuery<T> typedQuery = getEm().createQuery(cq);
		typedQuery.setMaxResults(limit);
		typedQuery.setFirstResult(start);

		return typedQuery;
	}

	public CriteriaQuery<T> order(CriteriaQuery<T> cq, CriteriaBuilder cb, Root<T> root, LinkedHashMap<String, String> orderColumn) throws IllegalArgumentException {
		List<Order> orderList = new ArrayList<Order>();
		for(String column : orderColumn.keySet()){
			if(orderColumn.get(column).equals("asc")){
				orderList.add(cb.asc(root.get(column)));
			} else {
				orderList.add(cb.desc(root.get(column)));
			}
		}
		cq.orderBy(orderList);

		return cq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> search(
			Integer start, Integer limit,
			LinkedHashMap<String,String> orderColumn,
			HashMap<String,Object> paramEquals,
			HashMap<String,Object> paramLike,
			HashMap<String,Object> paramGE,
			HashMap<String,Object> paramLE){

		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
		Root<T> entity = cq.from(typeParameterClass);

		cq = innerSearch(cb, cq, entity, orderColumn, paramEquals, paramLike, paramGE, paramLE);

		cq.select(entity);
		TypedQuery<T> q = paginate(cq, start, limit);

		return q.getResultList();
	}

	@SuppressWarnings("rawtypes")
	protected CriteriaQuery innerSearch(CriteriaBuilder cb, CriteriaQuery cq, Root<T> t, LinkedHashMap<String,String> orderColumn,
			HashMap<String,Object> paramEquals,
			HashMap<String,Object> paramLike,
			HashMap<String,Object> paramGE,
			HashMap<String,Object> paramLE){
		List<Predicate> listPred = new LinkedList<Predicate>();
		
		if(orderColumn != null){
			for(String ordCol: orderColumn.keySet()){
				if(orderColumn.get(ordCol).equalsIgnoreCase("asc")){
					cq.orderBy(cb.asc(t.get(ordCol)));
				}else{
					cq.orderBy(cb.desc(t.get(ordCol)));    
				}
			}
		}

		if(paramEquals != null){
			for(String column: paramEquals.keySet()){
				Predicate pred = cb.equal(t.get(column), paramEquals.get(column));
				listPred.add(pred);
			}
		}
		
		if(paramLike != null){
			for(String column: paramLike.keySet()){
				Predicate pred =  cb.like(t.<String>get(column), "%"+paramLike.get(column).toString()+"%");
				listPred.add(pred);
			}
		}
		
		if(paramGE != null){
			for(String column: paramGE.keySet()){
				Predicate pred =  cb.ge(t.<Number>get(column), (Number)paramGE.get(column));
				listPred.add(pred);
			}
		}
		
		if(paramLE != null){
			for(String column: paramLE.keySet()){
				Predicate pred =  cb.le(t.<Number>get(column), (Number)paramLE.get(column));
				listPred.add(pred);
			}
		}
		
		Predicate[] predArray = new Predicate[listPred.size()];
		listPred.toArray(predArray);
		cq.where(predArray);

		return cq;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long count(HashMap<String,Object> paramEquals, HashMap<String,Object> paramLike, HashMap<String,Object> paramGE, HashMap<String,Object> paramLE) throws Exception{
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
		Root<T> entity = cq.from(typeParameterClass);

		cq = innerSearch(cb, cq, entity, null, paramEquals, paramLike, paramGE, paramLE);

		cq.select((Expression<T>)cb.count(entity));

		return (Long) getEm().createQuery(cq).getSingleResult();
	}

	@Override
	public List<T> getAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn){
		return search(start, limit, orderColumn, null, null, null,null);
	}

	@Override
	public T getById(Object id) throws Exception {
		return getEm().find(typeParameterClass, id);
	}

	@Override
	public Long count() throws Exception{
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> entity = cq.from(typeParameterClass);

		cq.select(cb.count(entity));
		try{
			return getEm().createQuery(cq).getSingleResult();
		} catch (Exception e) {
			throw e;
		}
	}
}

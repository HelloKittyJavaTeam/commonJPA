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
	protected Class<T> typeParameterClass;
	private static Map<String, EntityManager> emMap = new HashMap<String, EntityManager>();

	public static String persistenceUnitName;

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

	protected CriteriaQuery<T> innerSearch(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> t, LinkedHashMap<String,String> orderColumn,
			HashMap<String,Object> paramEquals,
			HashMap<String,Object> paramLike,
			HashMap<String,Object> paramGE,
			HashMap<String,Object> paramLE){

		for(String ordCol: orderColumn.keySet()){
			if(orderColumn.get(ordCol).equalsIgnoreCase("asc")){
				cq.orderBy(cb.asc(t.get(ordCol)));
			}else{
				cq.orderBy(cb.desc(t.get(ordCol)));    
			}
		}
		List<Predicate> listPred = new LinkedList<Predicate>();

		for(String column: paramEquals.keySet()){
			Predicate pred = cb.equal(t.get(column), paramEquals.get(column));
			listPred.add(pred);
		}
		for(String column: paramLike.keySet()){
			Predicate pred =  cb.like(t.<String>get(column), "%"+paramLike.get(column).toString()+"%");
			listPred.add(pred);
		}
		for(String column: paramLE.keySet()){
			Predicate pred =  cb.le(t.<Number>get(column), (Number)paramLE.get(column));
			listPred.add(pred);
		}
		for(String column: paramGE.keySet()){
			Predicate pred =  cb.ge(t.<Number>get(column), (Number)paramLE.get(column));
			listPred.add(pred);
		}
		Predicate[] predArray = new Predicate[listPred.size()];
		listPred.toArray(predArray);
		cq.where(predArray);

		return cq;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long count(HashMap<String,Object> paramEquals, HashMap<String,Object> paramLike, HashMap<String,Object> paramGE, HashMap<String,Object> paramLE ) throws Exception{
		LinkedHashMap<String, String> orderColumn = new LinkedHashMap<String, String>();
		//        CriteriaQuery<Long> cq = (CriteriaQuery<Long>) cb.createQuery(typeParameterClass);
		//        Root<T> entity = cq.from(typeParameterClass);
		//
		//        List<Predicate> listPred = new LinkedList<Predicate>();
		//       
		//        for(String column: paramEquals.keySet()){
		//               Predicate pred = cb.equal(entity.get(column), paramEquals.get(column));
		//               listPred.add(pred);
		//        }
		//        for(String column: paramLike.keySet()){
		//               Predicate pred =  cb.like(entity.<String>get(column), "%"+paramLike.get(column).toString()+"%");
		//               listPred.add(pred);
		//        }
		//        for(String column: paramLE.keySet()){
		//               Predicate pred =  cb.le(entity.<Number>get(column), (Number)paramLE.get(column));
		//               listPred.add(pred);
		//        }
		//        for(String column: paramGE.keySet()){
		//               Predicate pred =  cb.ge(entity.<Number>get(column), (Number)paramLE.get(column));
		//               listPred.add(pred);
		//        }
		//        Predicate[] predArray = new Predicate[listPred.size()];
		//        listPred.toArray(predArray);
		//        cq.where(predArray);


		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
		Root<T> entity = cq.from(typeParameterClass);

		cq = innerSearch(cb, cq, entity, orderColumn, paramEquals, paramLike, paramGE, paramLE);

		cq.select((Expression<T>)cb.count(entity));

		return (Long) getEm().createQuery(cq).getSingleResult();
	}

	@Override
	public List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn){

		HashMap<String,Object> paramEquals = new HashMap<String, Object>();
		HashMap<String,Object> paramLike = new HashMap<String, Object>();
		HashMap<String,Object> paramGE = new HashMap<String, Object>();
		HashMap<String,Object> paramLE = new HashMap<String, Object>();

		return search(start, limit, orderColumn, paramEquals, paramLike, paramGE, paramLE);
	}

	@Override
	public T fetchById(Object id) throws Exception {
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

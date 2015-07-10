package it.hellokitty.gt.repository.impl;

import it.hellokitty.gt.repository.RepositoryExt;
import it.hellokitty.gt.repository.utils.RepositoryUtilsExt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositoryImplExt<T> extends RepositoryUtilsExt<T> implements RepositoryExt<T>{
	@Override
    public List<T> search(
                 Integer start, Integer limit,
                 LinkedHashMap<String,String> orderColumn,
                 HashMap<String,Object> paramEquals,
                 HashMap<String,Object> paramLike,
                 HashMap<String,Object> paramGE,
                 HashMap<String,Object> paramLE){
         
         CriteriaQuery<T> cq = innerSearch(orderColumn, paramEquals, paramLike, paramGE, paramLE);
         Root<T> t = cq.from(typeParameterClass);
          
          cq.select(t);
          TypedQuery<T> q = paginate(cq, start, limit);
         
          return q.getResultList();
    }
    
//    protected CriteriaQuery<T> innerSearch(LinkedHashMap<String,String> orderColumn,
//            HashMap<String,Object> paramEquals,
//            HashMap<String,Object> paramLike,
//            HashMap<String,Object> paramGE,
//            HashMap<String,Object> paramLE){
//    	CriteriaBuilder cb = getEm().getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
//        Root<T> t = cq.from(typeParameterClass);
//
//       
//        for(String ordCol: orderColumn.keySet()){
//               if(orderColumn.get(ordCol).equalsIgnoreCase("asc")){
//                      cq.orderBy(cb.asc(t.get(ordCol)));
//               }else{
//                      cq.orderBy(cb.desc(t.get(ordCol)));    
//               }
//        }
//        List<Predicate> listPred = new LinkedList<Predicate>();
//       
//        for(String column: paramEquals.keySet()){
//               Predicate pred = cb.equal(t.get(column), paramEquals.get(column));
//               listPred.add(pred);
//        }
//        for(String column: paramLike.keySet()){
//               Predicate pred =  cb.like(t.<String>get(column), "%"+paramLike.get(column).toString()+"%");
//               listPred.add(pred);
//        }
//        for(String column: paramLE.keySet()){
//               Predicate pred =  cb.le(t.<Number>get(column), (Number)paramLE.get(column));
//               listPred.add(pred);
//        }
//        for(String column: paramGE.keySet()){
//               Predicate pred =  cb.ge(t.<Number>get(column), (Number)paramLE.get(column));
//               listPred.add(pred);
//        }
//        Predicate[] predArray = new Predicate[listPred.size()];
//        listPred.toArray(predArray);
//        cq.where(predArray);
//        
//        return cq;
//    }
    
	@Override
	public List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn){
		
		HashMap<String,Object> paramEquals = new HashMap<String, Object>();
		HashMap<String,Object> paramLike = new HashMap<String, Object>();
        HashMap<String,Object> paramGE = new HashMap<String, Object>();
        HashMap<String,Object> paramLE = new HashMap<String, Object>();
        
        return search(start, limit, orderColumn, paramEquals, paramLike, paramGE, paramLE);
	}
	
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
	
	@Override
	@SuppressWarnings("unchecked")
	public Long count(HashMap<String,Object> paramEquals, HashMap<String,Object> paramLike, HashMap<String,Object> paramGE, HashMap<String,Object> paramLE ) throws Exception{
//		LinkedHashMap<String, String> orderColumn = new LinkedHashMap<String, String>();
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> cq = (CriteriaQuery<Long>) cb.createQuery(typeParameterClass);
        Root<T> entity = cq.from(typeParameterClass);

        List<Predicate> listPred = new LinkedList<Predicate>();
       
        for(String column: paramEquals.keySet()){
               Predicate pred = cb.equal(entity.get(column), paramEquals.get(column));
               listPred.add(pred);
        }
        for(String column: paramLike.keySet()){
               Predicate pred =  cb.like(entity.<String>get(column), "%"+paramLike.get(column).toString()+"%");
               listPred.add(pred);
        }
        for(String column: paramLE.keySet()){
               Predicate pred =  cb.le(entity.<Number>get(column), (Number)paramLE.get(column));
               listPred.add(pred);
        }
        for(String column: paramGE.keySet()){
               Predicate pred =  cb.ge(entity.<Number>get(column), (Number)paramLE.get(column));
               listPred.add(pred);
        }
        Predicate[] predArray = new Predicate[listPred.size()];
        listPred.toArray(predArray);
        cq.where(predArray);
		
//		CriteriaQuery<Long> cq = (CriteriaQuery<Long>) innerSearch(orderColumn, paramEquals, paramLike, paramGE, paramLE);
//		Root<T> entity = cq.from(typeParameterClass);
		

		cq.select(cb.count(entity));

		return getEm().createQuery(cq).getSingleResult();
	}
}

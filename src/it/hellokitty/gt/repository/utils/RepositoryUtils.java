package it.hellokitty.gt.repository.utils;



public abstract class RepositoryUtils<T>{// extends RepositoryUtilsExt<T> implements Repository<T>{
//    
//	@Override
//	public List<T> fetchAll(Integer start, Integer limit, LinkedHashMap<String, String> orderColumn, String user) throws Exception{
//		HashMap<String,Object> paramEquals = new HashMap<String, Object>();
//		paramEquals.put("userCreated", user);
//		paramEquals.put("active", true);
//		HashMap<String,Object> paramLike = new HashMap<String, Object>();
//        HashMap<String,Object> paramGE = new HashMap<String, Object>();
//        HashMap<String,Object> paramLE = new HashMap<String, Object>();
//        
//        return search(start, limit, orderColumn, paramEquals, paramLike, paramGE, paramLE);
//	}
//	
//	@Override
//	@SuppressWarnings("unchecked")
//	public Long count(HashMap<String,Object> paramEquals, HashMap<String,Object> paramLike, HashMap<String,Object> paramGE, HashMap<String,Object> paramLE ) throws Exception{
////		LinkedHashMap<String, String> orderColumn = new LinkedHashMap<String, String>();
//		CriteriaBuilder cb = getEm().getCriteriaBuilder();
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
//		
////		CriteriaQuery<Long> cq = (CriteriaQuery<Long>) innerSearch(orderColumn, paramEquals, paramLike, paramGE, paramLE);
////		Root<T> entity = cq.from(typeParameterClass);
//		
//
//		cq.select(cb.count(entity));
//
//		return getEm().createQuery(cq).getSingleResult();
//	}
//	
//	@Override
//	public void insert(BaseObject elem, String user) throws Exception{
//		EntityTransaction transaction;
//
//		elem.setUserCreated(user);
//		elem.setCreateDate(new Date());
//		elem.setActive(true);
//		
//		transaction = getEm().getTransaction();
//		try {
//			transaction.begin();
//		    getEm().persist(elem);
//		    transaction.commit(); 
//		} catch (Exception e) {
//			transaction.rollback();
//			throw e;
//		} finally {
//		    getEm().clear(); // Clears all the entities from the EntityManager
//		}
//	}
//
//	@Override
//	public void delete(BaseObject elem, String user) throws Exception{
//		EntityTransaction transaction = null;
//		try {
//			transaction = getEm().getTransaction();
//			transaction.begin();
//		    
//			elem.setUserUpdate(user);
//			elem.setUpdate(new Date());
//			elem.setActive(false);
//				
//			getEm().merge(elem);
//
//		    transaction.commit();
//		} catch (Exception e) {
//		   	transaction.rollback();
//		   	throw e;
//		} finally {
//			getEm().clear(); // Clears all the entities from the EntityManager
//		}
//	}
//
//	@Override
//	public BaseObject merge(BaseObject elem, String user) throws Exception {
//		BaseObject result = null;
//
//		EntityTransaction transaction= null;
//		try {
//			transaction = getEm().getTransaction();
//			transaction.begin();
//			
//			elem.setUserUpdate(user);
//			elem.setUpdate(new Date());
//		    result = getEm().merge(elem);
//		    
//		    transaction.commit();
//		} catch (Exception e) {
//		    transaction.rollback();
//		    throw e;
//		} finally {
//			getEm().clear(); // Clears all the entities from the EntityManager
//		}
//		return result;
//	}
}

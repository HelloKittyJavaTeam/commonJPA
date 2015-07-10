package it.hellokitty.gt.service.impl;

import it.ferrari.gt.service.impl.ServiceImpl;
import it.hellokitty.gt.entity.Bulletin;
import it.hellokitty.gt.repository.BulletinRepository;
import it.hellokitty.gt.repository.impl.BulletinRepositoryImpl;
import it.hellokitty.gt.service.BulletinService;

public class BulletinServiceImpl extends ServiceImpl<Bulletin> implements BulletinService {
	BulletinRepository bulletinRepository = new BulletinRepositoryImpl();
	
//	@Override
//	public Bulletin fetchById(Long id) throws IllegalArgumentException, Exception {
//		if(id == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be null.");
//		}
//		
//		if(id < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be < 0.");
//		}
//		
//		return bulletinRepository.fetchById(id);
//	}
//
//	@Override
//	public List<Bulletin> fetchAll(Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
//		if(start == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be null.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Current value:"+start+".");
//		}
//		
//		if(limit == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit patameter can't be null.");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Current value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList paramete can't be null.");
//		}
//		
//		return bulletinRepository.fetchAll(start, limit, cdList);
//	}
//
//	@Override
//	public List<Bulletin> fetchAll(String user, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//		}
//		
//		if(start == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be null.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Current value:"+start+".");
//		}
//		
//		if(limit == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be null.");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Current value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null.");
//		}
//		
//		return bulletinRepository.fetchAll(user, start, limit, cdList);
//	}
//
//	@Override
//	public void insert(Bulletin elem, String user) throws IllegalArgumentException, Exception {
//		if(elem == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
//		}
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//		}
//		
//		bulletinRepository.insert(elem, user);
//	}
//
//	@Override
//	public void delete(Bulletin elem, String user) throws IllegalArgumentException, Exception {
//		if(elem == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
//		}
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//		}
//		
//		bulletinRepository.delete(elem, user);
//	}
//
//	@Override
//	public Bulletin update(Bulletin elem, String user) throws IllegalArgumentException, Exception {
//		if(elem == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
//		}
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//			
//		}
//		
//		return bulletinRepository.update(elem, user);
//	}
//
//	@Override
//	public Bulletin merge(Bulletin elem, String user) throws IllegalArgumentException, Exception {
//		if(elem == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
//		}
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//			
//		}
//		
//		return bulletinRepository.merge(elem, user);
//	}
//
//	@Override
//	public Long count(String user) throws IllegalArgumentException, Exception {
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//			
//		}
//		
//		return bulletinRepository.count(user);
//	}
//
//	@Override
//	public List<Bulletin> fetchByTag(String tag, int start, int limit, List<ColumnDirection> cdList) throws Exception {
//		if(tag == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be null.");
//		}
//		
//		if(tag.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be empty.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Actual value:"+start+".");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Actual value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null. Actual value:"+cdList+".");
//		}
//		
//		return bulletinRepository.fetchByTag(tag, start, limit, cdList);
//	}
//
//	@Override
//	public List<Bulletin> fetchByTagLike(String tag, int start, int limit, List<ColumnDirection> cdList) throws Exception {
//		if(tag == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be null.");
//		}
//		
//		if(tag.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be empty.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Actual value:"+start+".");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Actual value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null. Actual value:"+cdList+".");
//		}
//		
//		return bulletinRepository.fetchByTagLike(tag, start, limit, cdList);
//	}
//
//	@Override
//	public List<Bulletin> fetchByTag(String tag, String user, int start, int limit, List<ColumnDirection> cdList) throws Exception {
//		if(tag == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be null.");
//		}
//		
//		if(tag.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be empty.");
//		}
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Actual value:"+start+".");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Actual value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null. Actual value:"+cdList+".");
//		}
//		
//		return bulletinRepository.fetchByTag(tag, user, start, limit, cdList);
//	}
//
//	@Override
//	public List<Bulletin> fetchByTagLike(String tag, String user, int start, int limit, List<ColumnDirection> cdList) throws Exception {
//		if(tag == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be null.");
//		}
//		
//		if(tag.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - tag parameter can't be empty.");
//		}
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Actual value:"+start+".");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Actual value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null. Actual value:"+cdList+".");
//		}
//		
//		return bulletinRepository.fetchByTagLike(tag, user, start, limit, cdList);
//	}
//
//	@Override
//	public List<Bulletin> fetchByUser(String user, int start, int limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Actual value:"+start+".");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Actual value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null. Actual value:"+cdList+".");
//		}
//		
//		return bulletinRepository.fetchByUser(user, start, limit, cdList);
//	}
//
//	@Override
//	public List<Bulletin> search(String content, String user, String language, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
//		if(content == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - content parameter can't be null.");
//		}
//		
//		if(user == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
//		}
//		
//		if(user.equals("")){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
//		}
//		
//		if(language == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - language parameter can't be null.");
//		}
//		
//		if(start == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be null.");
//		}
//		
//		if(start < 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Actual value:"+start+".");
//		}
//		
//		if(limit == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be null.");
//		}
//		
//		if(limit <= 0){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Actual value:"+limit+".");
//		}
//		
//		if(cdList == null){
//			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null. Actual value:"+cdList+".");
//		}
//		
//		return bulletinRepository.search(content, user, language, start, limit, cdList);
//	}
}

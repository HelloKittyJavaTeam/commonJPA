package it.hellokitty.gt.service.impl;

import it.hellokitty.gt.entity.GeoAreas;
import it.hellokitty.gt.repository.GeoAreasRepository;
import it.hellokitty.gt.repository.impl.GeoAreasRepositoryImpl;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.GeoAreaService;

import java.util.List;

public class GeoAreasServiceImpl implements GeoAreaService{
	GeoAreasRepository geoAreasRepository = new GeoAreasRepositoryImpl();

	@Override
	public GeoAreas fetchById(Long id) throws IllegalArgumentException, Exception {
		if(id == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be null.");
		}
		
		if(id < 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be < 0.");
		}
		
		return geoAreasRepository.fetchById(id);
	}

	@Override
	public List<GeoAreas> fetchAll(Integer start, Integer limit, List<ColumnDirection> cdList) throws Exception {
		if(start == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be null.");
		}
		
		if(start < 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Current value:"+start+".");
		}
		
		if(limit == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit patameter can't be null.");
		}
		
		if(limit <= 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Current value:"+limit+".");
		}
		
		if(cdList == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList paramete can't be null.");
		}
		
		return geoAreasRepository.fetchAll(start, limit, cdList);
	}

	@Override
	public List<GeoAreas> fetchAll(String user, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
		}
		
		if(start == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be null.");
		}
		
		if(start < 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - start parameter can't be < 0. Current value:"+start+".");
		}
		
		if(limit == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be null.");
		}
		
		if(limit <= 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - limit parameter can't be <= 0. Current value:"+limit+".");
		}
		
		if(cdList == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - cdList parameter can't be null.");
		}
		
		return geoAreasRepository.fetchAll(user, start, limit, cdList);
	}

//	@Override
//	public void insert(GeoAreas elem, String user) throws IllegalArgumentException, Exception {
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
//		geoAreasRepository.insert(elem, user);
//	}
//
//	@Override
//	public void delete(GeoAreas elem, String user) throws IllegalArgumentException, Exception {
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
//		geoAreasRepository.delete(elem, user);
//	}
//
//	@Override
//	public GeoAreas update(GeoAreas elem, String user) throws IllegalArgumentException, Exception {
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
//		return geoAreasRepository.update(elem, user);
//	}
//
//	@Override
//	public GeoAreas merge(GeoAreas elem, String user) throws IllegalArgumentException, Exception {
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
//		return geoAreasRepository.merge(elem, user);
//	}

	@Override
	public Long count(String user) throws IllegalArgumentException, Exception {
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
			
		}
		
		return geoAreasRepository.count(user);
	}
}

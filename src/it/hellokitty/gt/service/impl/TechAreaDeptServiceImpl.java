package it.hellokitty.gt.service.impl;

import it.hellokitty.gt.entity.TechAreaDept;
import it.hellokitty.gt.repository.TechAreaDeptRepository;
import it.hellokitty.gt.repository.impl.TechAreaDeptRepositoryImpl;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.TechAreaDeptService;

import java.util.List;

public class TechAreaDeptServiceImpl implements TechAreaDeptService {
	TechAreaDeptRepository techAreaDeptRepository = new TechAreaDeptRepositoryImpl();

	@Override
	public TechAreaDept fetchById(Long id) throws IllegalArgumentException, Exception {
		if(id == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be null.");
		}
		
		if(id < 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be < 0.");
		}
		
		return techAreaDeptRepository.fetchById(id);
	}

	@Override
	public List<TechAreaDept> fetchAll(Integer start, Integer limit, List<ColumnDirection> cdList) throws Exception {
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
		
		return techAreaDeptRepository.fetchAll(start, limit, cdList);
	}

	@Override
	public List<TechAreaDept> fetchAll(String user, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
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
		
		return techAreaDeptRepository.fetchAll(user, start, limit, cdList);
	}

	@Override
	public void insert(TechAreaDept elem, String user) throws IllegalArgumentException, Exception {
		if(elem == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
		}
		
		techAreaDeptRepository.insert(elem, user);
	}

	@Override
	public void delete(TechAreaDept elem, String user) throws IllegalArgumentException, Exception {
		if(elem == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
		}
		
		techAreaDeptRepository.delete(elem, user);
	}

	@Override
	public TechAreaDept update(TechAreaDept elem, String user) throws IllegalArgumentException, Exception {
		if(elem == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
			
		}
		
		return techAreaDeptRepository.update(elem, user);
	}

	@Override
	public TechAreaDept merge(TechAreaDept elem, String user) throws IllegalArgumentException, Exception {
		if(elem == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - elem parameter can't be null.");
		}
		
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
			
		}
		
		return techAreaDeptRepository.merge(elem, user);
	}

	@Override
	public Long count(String user) throws IllegalArgumentException, Exception {
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
			
		}
		
		return techAreaDeptRepository.count(user);
	}
}

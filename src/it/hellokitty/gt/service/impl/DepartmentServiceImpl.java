package it.hellokitty.gt.service.impl;

import java.util.List;

import it.hellokitty.gt.entity.Department;
import it.hellokitty.gt.repository.DepartmentRepository;
import it.hellokitty.gt.repository.impl.DepartmentRepositoryImpl;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();

	@Override
	public Department fetchById(Long id) throws IllegalArgumentException, Exception {
		if(id == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be null.");
		}
		
		if(id < 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be < 0.");
		}
		
		return departmentRepository.fetchById(id);
	}

	@Override
	public List<Department> fetchAll(Integer start, Integer limit, List<ColumnDirection> cdList) throws Exception {
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
		
		return departmentRepository.fetchAll(start, limit, cdList);
	}

	@Override
	public List<Department> fetchAll(String user, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
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
		
		return departmentRepository.fetchAll(user, start, limit, cdList);
	}

	@Override
	public Long count(String user) throws IllegalArgumentException, Exception {
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
			
		}
		
		return departmentRepository.count(user);
	}

}

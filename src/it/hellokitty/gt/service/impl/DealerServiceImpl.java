package it.hellokitty.gt.service.impl;

import it.hellokitty.gt.entity.Dealers;
import it.hellokitty.gt.repository.DealerRepository;
import it.hellokitty.gt.repository.impl.DealerRepositoryImpl;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.DealerService;

import java.util.List;

public class DealerServiceImpl implements DealerService {
	DealerRepository dealerRepository = new DealerRepositoryImpl();

	@Override
	public Dealers fetchById(Long id) throws IllegalArgumentException, Exception {
		if(id == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be null.");
		}
		
		if(id < 0){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - id parameter can't be < 0.");
		}
		
		return dealerRepository.fetchById(id);
	}

	@Override
	public List<Dealers> fetchAll(Integer start, Integer limit, List<ColumnDirection> cdList) throws Exception {
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
		
		return dealerRepository.fetchAll(start, limit, cdList);
	}

	@Override
	public List<Dealers> fetchAll(String user, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception {
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
		
		return dealerRepository.fetchAll(user, start, limit, cdList);
	}

	@Override
	public Long count(String user) throws IllegalArgumentException, Exception {
		if(user == null){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be null.");
		}
		
		if(user.equals("")){
			throw new IllegalArgumentException(this.getClass().getPackage()+" - "+this.getClass()+" - user parameter can't be empty.");
			
		}
		
		return dealerRepository.count(user);
	}
}

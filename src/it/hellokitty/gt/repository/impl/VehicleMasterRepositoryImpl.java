package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.VehicleMaster;
import it.hellokitty.gt.repository.VehicleMasterRepository;

public class VehicleMasterRepositoryImpl extends RepositoryImpl<VehicleMaster> implements VehicleMasterRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = VehicleMaster.class;
	}
}

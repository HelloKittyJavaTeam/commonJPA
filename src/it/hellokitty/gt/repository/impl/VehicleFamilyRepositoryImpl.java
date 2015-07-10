package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.VehicleFamily;
import it.hellokitty.gt.repository.VehicleFamilyRepository;

public class VehicleFamilyRepositoryImpl extends RepositoryImpl<VehicleFamily> implements VehicleFamilyRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = VehicleFamily.class;
	}
}

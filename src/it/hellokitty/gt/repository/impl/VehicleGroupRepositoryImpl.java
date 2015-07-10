package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.VehicleGroup;
import it.hellokitty.gt.repository.VehicleGroupRepository;

public class VehicleGroupRepositoryImpl extends RepositoryImpl<VehicleGroup> implements VehicleGroupRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = VehicleGroup.class;
	}
}
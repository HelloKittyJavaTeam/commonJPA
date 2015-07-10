package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.GeoRegions;
import it.hellokitty.gt.repository.GeoRegionsRepository;

public class GeoRegionsRepositoryImpl extends RepositoryImpl<GeoRegions> implements GeoRegionsRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = GeoRegions.class;
	}
}

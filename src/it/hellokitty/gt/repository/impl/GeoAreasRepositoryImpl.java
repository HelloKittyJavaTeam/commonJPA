package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.GeoAreas;
import it.hellokitty.gt.repository.GeoAreasRepository;

public class GeoAreasRepositoryImpl extends RepositoryImpl<GeoAreas> implements GeoAreasRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = GeoAreas.class;
	}
}

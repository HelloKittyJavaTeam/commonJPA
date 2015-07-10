package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.GeoCountries;
import it.hellokitty.gt.repository.GeoCountriesRepository;

public class GeoCountriesRepositoryImpl extends RepositoryImpl<GeoCountries> implements GeoCountriesRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = GeoCountries.class;
	}
}

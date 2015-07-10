package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.Dealers;
import it.hellokitty.gt.repository.DealerRepository;

public class DealerRepositoryImpl extends RepositoryImpl<Dealers> implements DealerRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = Dealers.class;
	}
}
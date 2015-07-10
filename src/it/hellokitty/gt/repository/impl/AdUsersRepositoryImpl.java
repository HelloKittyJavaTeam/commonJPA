package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.AdUsers;
import it.hellokitty.gt.repository.AdUsersRepository;

public class AdUsersRepositoryImpl extends RepositoryImpl<AdUsers> implements AdUsersRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = AdUsers.class;
	}
}

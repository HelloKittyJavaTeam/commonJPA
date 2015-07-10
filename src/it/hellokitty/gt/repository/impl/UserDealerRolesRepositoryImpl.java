package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.UserDealerRoles;
import it.hellokitty.gt.repository.UserDealerRolesRepository;

public class UserDealerRolesRepositoryImpl extends RepositoryImpl<UserDealerRoles> implements UserDealerRolesRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = UserDealerRoles.class;
	}
}

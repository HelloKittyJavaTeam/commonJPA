package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.DealerRoles;
import it.hellokitty.gt.repository.DealerRolesRepository;

public class DealerRolesRepositoryImpl extends RepositoryImpl<DealerRoles> implements DealerRolesRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = DealerRoles.class;
	}
}

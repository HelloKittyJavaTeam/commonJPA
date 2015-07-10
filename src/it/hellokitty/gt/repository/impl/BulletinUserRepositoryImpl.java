package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.BulletinUser;
import it.hellokitty.gt.repository.BulletinUserRepository;

public class BulletinUserRepositoryImpl extends RepositoryImpl<BulletinUser> implements BulletinUserRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = BulletinUser.class;
	}
}
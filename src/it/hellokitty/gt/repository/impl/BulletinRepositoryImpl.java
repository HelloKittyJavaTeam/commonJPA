package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.Bulletin;
import it.hellokitty.gt.repository.BulletinRepository;

public class BulletinRepositoryImpl extends RepositoryImpl<Bulletin> implements BulletinRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = Bulletin.class;
	}
}
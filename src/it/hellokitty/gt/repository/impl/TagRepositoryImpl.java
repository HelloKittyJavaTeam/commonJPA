package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.Tag;
import it.hellokitty.gt.repository.TagRepository;

public class TagRepositoryImpl extends RepositoryImpl<Tag> implements TagRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = Tag.class;
	}
}
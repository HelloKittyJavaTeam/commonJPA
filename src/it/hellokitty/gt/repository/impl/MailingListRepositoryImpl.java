package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.MailingList;
import it.hellokitty.gt.repository.MailingListRepository;

public class MailingListRepositoryImpl extends RepositoryImpl<MailingList> implements MailingListRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = MailingList.class;
	}
}
package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.EmailContact;
import it.hellokitty.gt.repository.EmailContactRepository;

public class EmailContactRepositoryImpl extends RepositoryImpl<EmailContact> implements EmailContactRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = EmailContact.class;
	}
}
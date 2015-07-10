package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.AttachmentHistory;
import it.hellokitty.gt.repository.AttachmentHistoryRepository;

public class AttachmentHistoryRepositoryImpl extends RepositoryImpl<AttachmentHistory> implements AttachmentHistoryRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = AttachmentHistory.class;
	}
}

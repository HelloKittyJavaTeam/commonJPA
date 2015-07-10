package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.Attachment;
import it.hellokitty.gt.repository.AttachmentRepository;

public class AttachmentRepositoryImpl extends RepositoryImpl<Attachment> implements AttachmentRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = Attachment.class;
	}
}

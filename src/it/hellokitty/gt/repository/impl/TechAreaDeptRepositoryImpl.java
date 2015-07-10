package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.TechAreaDept;
import it.hellokitty.gt.repository.TechAreaDeptRepository;

public class TechAreaDeptRepositoryImpl extends RepositoryImpl<TechAreaDept> implements TechAreaDeptRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = TechAreaDept.class;
	}
}

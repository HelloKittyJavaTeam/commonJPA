package it.hellokitty.gt.repository.impl;

import it.ferrari.gt.repository.impl.RepositoryImpl;
import it.hellokitty.gt.entity.Department;
import it.hellokitty.gt.repository.DepartmentRepository;

public class DepartmentRepositoryImpl extends RepositoryImpl<Department> implements DepartmentRepository{
	{
		persistenceUnitName = "BULLETIN_PU";
		typeParameterClass = Department.class;
	}
}

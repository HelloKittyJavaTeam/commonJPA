package it.hellokitty.gt.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;






import it.hellokitty.gt.entity.AdUsers;
import it.hellokitty.gt.repository.AdUsersRepository;
import it.hellokitty.gt.repository.impl.AdUsersRepositoryImpl;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.AdUsersService;

public class AdUsersServiceImpl extends Service<AdUsers> implements AdUsersService{
	AdUsersRepository adUsersRepository = new AdUsersRepositoryImpl();
}

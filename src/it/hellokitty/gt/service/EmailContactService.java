package it.hellokitty.gt.service;

import java.util.List;

import it.ferrari.gt.service.Service;
import it.hellokitty.gt.entity.EmailContact;
import it.hellokitty.gt.repository.utils.ColumnDirection;

public interface EmailContactService extends Service<EmailContact> {
	List<EmailContact> fetchByName(String name, String user, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception;
	Long count(String user) throws IllegalArgumentException, Exception;
	List<EmailContact> search(String content, String user, Integer start, Integer limit, List<ColumnDirection> cdList) throws IllegalArgumentException, Exception;
}

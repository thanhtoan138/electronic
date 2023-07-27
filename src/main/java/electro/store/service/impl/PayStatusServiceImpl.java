package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.PayStatus;
import electro.store.repository.PayStatusRepository;
import electro.store.service.PayStatusService;

@Service
public class PayStatusServiceImpl implements PayStatusService{
	@Autowired
	PayStatusRepository payDAO;
	
	@Override
	public List<PayStatus> getAll() {
		return payDAO.findAll();
	}

}

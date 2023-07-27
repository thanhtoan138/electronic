package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.Shipper;
import electro.store.repository.ShipperRepository;
import electro.store.service.ShipperService;

@Service
public class ShipperServiceImpl implements ShipperService{
	@Autowired
	ShipperRepository shipDAO;
	
	@Override
	public List<Shipper> findAll() {
		return shipDAO.findAll();
	}


}

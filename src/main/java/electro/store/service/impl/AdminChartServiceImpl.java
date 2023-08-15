package electro.store.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.repository.OrderRepository;
import electro.store.repository.ProductRepository;
import electro.store.service.AdminChartService;
@Service
public class AdminChartServiceImpl implements AdminChartService{
	@Autowired
	OrderRepository dao;
	
	@Autowired
	ProductRepository ddao;
	
	@Override
	public String [][] getQuantityChartByMonth() {
		return dao.getQuantityChartByMonth();
	}

	@Override
	public String[][] getPriceChartByMonth() {
		return dao.getPriceChartByMonth();
	}

	@Override
	public String[][] getQuantityChartBrand() {
		return ddao.getQuantityChartBrand();
	}

	@Override
	public String[][] getQuantityChartCategory() {
		return ddao.getQuantityChartCategory();
	}


}

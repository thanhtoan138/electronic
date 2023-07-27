package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.OrderDetail;
import electro.store.repository.OrderDetailRepository;
import electro.store.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailRepository detailDAO;
	
	@Override
	public List<OrderDetail> getAllOrderDetail() {
		return detailDAO.findAll();
	}

	@Override
	public OrderDetail updateShip(OrderDetail orDetail) {
		return  detailDAO.save(orDetail);
	}

	@Override
	public void deleteOrderDetail(Integer id) {
		detailDAO.deleteById(id);
	}
	

}

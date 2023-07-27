package electro.store.service;

import java.util.List;

import electro.store.entity.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> getAllOrderDetail();

	OrderDetail updateShip(OrderDetail orDetail);

	void deleteOrderDetail(Integer id);
	
}

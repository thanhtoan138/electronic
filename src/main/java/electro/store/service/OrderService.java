package electro.store.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import electro.store.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);
	
	Object findById(Integer id);

	List<Order> findByUsername(String username);

	List<Order> getAll();

	Order update(Order order);

	void delete(Integer id);

	

	
	
}

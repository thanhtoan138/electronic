package electro.store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.Order;
import electro.store.entity.OrderDetail;
import electro.store.entity.PayStatus;
import electro.store.entity.Product;
import electro.store.entity.Shipper;
import electro.store.repository.OrderRepository;
import electro.store.repository.OrderDetailRepository;
import electro.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository dao;
	
	@Autowired
	OrderDetailRepository ddao;
	
	@Autowired
	ProductServiceImpl pddao;
	
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
//		Shipper s = new Shipper();
//		s.setId(1);
//		PayStatus p = new PayStatus();
//		p.setId(1);
		//convert gia tri json thanh doi tuong class
		Order order = mapper.convertValue(orderData, Order.class);
//		order.setShipper(s);
//		order.setPaystatus(p);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		
		return order;
	}
	
	@Override
	public Object findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Order> getAll() {
		return dao.findAll();
	}

	@Override
	public Order update(Order order) {
		return dao.save(order);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}
	public void updateProd( int id, int quantityprod) {
		Product prod =  pddao.findById(id);
		prod.setQuantityprod(quantityprod); 
		pddao.update(prod);
		
	}

	@Override
	public void updateStatusPayment() {
		dao.updateStatusPayment();
	}

}

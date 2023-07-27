package electro.store.controller.rest;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.entity.Order;
import electro.store.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	@GetMapping()
	public List<Order> showAll() {
		return orderService.getAll();
	}
	@PutMapping("{id}")
	public Order update(@PathVariable("id") Integer id,@RequestBody Order order) {
		return orderService.update(order);
	}
	@DeleteMapping("{id}")
	public void delte(@PathVariable("id") Integer id) {
		orderService.delete(id);
	}
}

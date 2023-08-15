package electro.store.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.entity.OrderDetail;
import electro.store.service.OrderDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order-detail")
public class OrderDetailRestController {
	@Autowired OrderDetailService detailService;
	
	@GetMapping()
	public List<OrderDetail> getAll(){
		return detailService.getAllOrderDetail();
	}
	
	@PutMapping("{id}")
	public OrderDetail update(@PathVariable("id") Integer id,@RequestBody OrderDetail orDetail) {
		return detailService.updateShip(orDetail);
	}
	
	@DeleteMapping("{id}")
	public void deleteByProduct_IdandUsername_Id(@PathVariable("id") Integer id ){
		detailService.deleteOrderDetail(id);
	}
}

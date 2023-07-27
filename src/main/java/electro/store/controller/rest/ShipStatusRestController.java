package electro.store.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.entity.Shipper;
import electro.store.service.ShipperService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shipstatus")
public class ShipStatusRestController {
	@Autowired
	ShipperService shipService;
	
	@GetMapping()
	public List<Shipper> getAll(){
		return shipService.findAll();
				
	}
	
}

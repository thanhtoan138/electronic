package electro.store.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.entity.PayStatus;
import electro.store.service.PayStatusService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/paystatus")
public class PayStatusRestController {
	@Autowired
	PayStatusService payService;
	
	@GetMapping()
	public List<PayStatus> Show() {
		return payService.getAll();
	}
}

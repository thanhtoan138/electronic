package electro.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import electro.store.service.OrderService;
import electro.store.service.ShipperService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShipperService shipService;
	
	@RequestMapping("/order/checkout")
	public String home() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
}

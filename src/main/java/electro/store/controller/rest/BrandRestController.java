package electro.store.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.entity.Brand;
import electro.store.service.BrandService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandRestController {
	@Autowired
	BrandService brandService;
	
	@GetMapping()
	public List<Brand> getAll() {
		return brandService.findAll();
	}
	
}

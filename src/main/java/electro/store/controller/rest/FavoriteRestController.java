package electro.store.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.entity.Favorite;
import electro.store.service.FavoriteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoriteRestController {
	
	@Autowired
	FavoriteService favoriteService;
	
	@GetMapping()
	public List<Favorite> getAll() {
		return favoriteService.findAll();
	}
	
	@PostMapping
	public Favorite create(@RequestBody Favorite favorite) {
		return favoriteService.create(favorite);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable("id") Integer id) {
		favoriteService.deleteById(id);
	}
	
	@DeleteMapping("{product_id}/{username_id}")
	@Transactional
	public void deleteByProduct_IdandUsername_Id(
				@PathVariable("product_id") Integer product_id, 
				@PathVariable("username_id") String username_id) {
		favoriteService.deleteByProduct_IdandUsername_Id(product_id,username_id);
	}
}

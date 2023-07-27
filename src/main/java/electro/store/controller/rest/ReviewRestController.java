package electro.store.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.entity.Review;
import electro.store.service.ReviewService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reviews")
public class ReviewRestController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	public Review create(@RequestBody Review review) {
		return reviewService.create(review);
	}
}

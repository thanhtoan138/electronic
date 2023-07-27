package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import electro.store.entity.Review;
import electro.store.repository.ReviewRepository;
import electro.store.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public void save(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public Page<Review> findByProduct(Integer id, Pageable pageable) {
		return reviewRepository.findByProduct(id,pageable);
	}

	@Override
	public Review create(Review review) {
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> findByProductId(Integer id) {
		return reviewRepository.findByProudctId(id);
	}

	

}

package electro.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	@Query("Select r from Review r where r.product.id=?1")
	Page<Review> findByProduct(Integer id, Pageable pageable);
	
	@Query("Select r from Review r where r.product.id=?1")
	List<Review> findByProudctId(Integer id);

}

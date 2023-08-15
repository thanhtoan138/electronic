package electro.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import electro.store.entity.Product;

public interface ProductService {

	List<Product> findAll();

	List<Product> findByCategoryId(Integer cid);
	
	Page<Product> findByCategoryId(Integer integer, Pageable pageable);
	
	Page<Product> findByBrandId(Integer integer, Pageable pageable);
	
	Page<Product> findAll(Pageable pageable);
	
	Page<Product> findByNameContaining(String name, Pageable pageable);

	Product findById(Integer id);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<Product> findTop3ByCategoryId(Integer c1);

	Product updateById(Integer QuantityProd ,Integer id);
}

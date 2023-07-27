package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import electro.store.entity.Product;
import electro.store.repository.ProductRepository;
import electro.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findByCategoryId(Integer cid) {
		return productRepository.findByCategoryId(cid);
	}
	
	@Override
	public Page<Product> findByCategoryId(Integer cid, Pageable pageable) {
		return productRepository.findByCategoryId(cid, pageable);
	}
	
	@Override
	public Page<Product> findByBrandId(Integer bid, Pageable pageable) {
		return productRepository.findByBrandId(bid, pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return productRepository.findByNameContaining(name, pageable);
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public List<Product> findTop3ByCategoryId(Integer c1) {
		return productRepository.findTop3ByCategoryId(c1);
	}
}

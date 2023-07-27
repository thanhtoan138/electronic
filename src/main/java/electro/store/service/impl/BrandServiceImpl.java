package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.Brand;
import electro.store.repository.BrandRepository;
import electro.store.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	BrandRepository brandRepository;

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

}

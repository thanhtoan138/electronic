package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.Category;
import electro.store.repository.CategoryRepository;
import electro.store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository cdao;
	
	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}
	
}

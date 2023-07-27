package electro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import electro.store.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}

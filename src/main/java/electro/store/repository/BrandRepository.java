package electro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import electro.store.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

}

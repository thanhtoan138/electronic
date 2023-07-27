package electro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import electro.store.entity.Description;

public interface DescriptionRepository extends JpaRepository<Description, Integer>{

}

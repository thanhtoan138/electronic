package electro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import electro.store.entity.Config;

public interface ConfigRepository extends JpaRepository<Config, Integer>{

}

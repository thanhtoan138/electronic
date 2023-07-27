package electro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import electro.store.entity.PayStatus;

public interface PayStatusRepository extends JpaRepository<PayStatus, Integer>  {
		
}

package electro.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);
	
	@Query(value = "SELECT * FROM QuantityChartByMonth", nativeQuery = true)
    String [][]getQuantityChartByMonth();  
    
    @Query(value = "SELECT * FROM PriceChartByMonth", nativeQuery = true)
    String [][]getPriceChartByMonth(); 
}

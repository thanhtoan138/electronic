package electro.store.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);
	
	@Query(value = "SELECT * FROM QuantityChartByMonth", nativeQuery = true)
    String [][]getQuantityChartByMonth();  
    
    @Query(value = "SELECT * FROM PriceChartByMonth", nativeQuery = true)
    String [][]getPriceChartByMonth();
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE orders JOIN ( SELECT Id FROM orders ORDER BY Id DESC LIMIT 1 ) AS latest_order ON orders.Id = latest_order.Id SET Pay_Status = 2;", nativeQuery = true)
    void updateStatusPayment();
}

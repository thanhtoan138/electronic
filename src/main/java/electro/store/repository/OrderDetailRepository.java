package electro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	
	@Modifying
	@Query("Delete from OrderDetail d where d.product.id=?1 and d.order.id=?2")
	void deleteByOrderDetail(Integer product_id, Integer order_id);
	
}

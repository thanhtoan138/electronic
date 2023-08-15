package electro.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("Select p from Product p where p.category.id=?1")
	List<Product> findByCategoryId(Integer cid);
	
	@Query("Select p from Product p where p.category.id=?1")
	Page<Product> findByCategoryId(Integer cid, Pageable pageable);
	
	@Query("Select p from Product p where p.brand.id=?1")
	Page<Product> findByBrandId(Integer bid, Pageable pageable);
	
	Page<Product> findByNameContaining(String name, Pageable pageable);
	
	@Query(value="Select * from products where Category_Id=?1 limit 3", nativeQuery=true)
	List<Product> findTop3ByCategoryId(Integer c1);  
	
	@Query(value = "SELECT * FROM QuantityChartCategory", nativeQuery = true)
    String [][]getQuantityChartCategory();  
	
	@Query(value = "SELECT * FROM QuantityChartBrand", nativeQuery = true)
    String [][]getQuantityChartBrand(); 
	
	@Query(value="Update products set QuantityProd =?1  where id =?2", nativeQuery = true)
	Product updateQuantityProdById(Integer QuantityProd,Integer id);
}

package electro.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.Favorite;
import electro.store.entity.Product;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
	
	@Query("Select f.product from Favorite f where f.account.username=?1")
	Page<Product> findFavoriteProductByUser(String username, Pageable pageable);
	
	@Query("Select f.product from Favorite f where f.account.username=?1 and f.product.category.id=?2")
	Page<Product> findFavoriteByCategoryId(String username, Integer cid, Pageable pageable);
	
	@Query("Select f.product from Favorite f where f.account.username=?1 and f.product.brand.id=?2")
	Page<Product> findFavoriteByBrandId(String username, Integer bid, Pageable pageable);
	
	@Query("Select f.product from Favorite f where f.account.username=?1")
	List<Product> findFavoriteProductByUser(String username);
	
	@Modifying
	@Query("Delete from Favorite f where f.product.id=?1 and f.account.username=?2")
	void deleteByProduct_IdandUsername_Id(Integer product_id, String username_id);
	
}

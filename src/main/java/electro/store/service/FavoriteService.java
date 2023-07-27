package electro.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import electro.store.entity.Favorite;
import electro.store.entity.Product;

public interface FavoriteService {

	Page<Product> findFavoriteProductByUser(String username, Pageable pageable);

	Page<Product> findFavoriteByCategoryId(String username, Integer cid, Pageable pageable);

	Page<Product> findFavoriteByBrandId(String username, Integer bid, Pageable pageable);

	Favorite create(Favorite favorite);

	void deleteById(Integer id);

	List<Favorite> findAll();

	List<Product> findFavoriteProductByUser(String username);

	void deleteByProduct_IdandUsername_Id(Integer product_id, String username_id);

}

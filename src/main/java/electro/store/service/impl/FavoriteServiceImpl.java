package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import electro.store.entity.Favorite;
import electro.store.entity.Product;
import electro.store.repository.FavoriteRepository;
import electro.store.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{
	
	@Autowired
	FavoriteRepository favoriteRepository;

	@Override
	public Page<Product> findFavoriteProductByUser(String username, Pageable pageable) {
		return favoriteRepository.findFavoriteProductByUser(username,pageable);
	}

	@Override
	public Page<Product> findFavoriteByCategoryId(String username, Integer cid, Pageable pageable) {
		return favoriteRepository.findFavoriteByCategoryId(username,cid,pageable);
	}

	@Override
	public Page<Product> findFavoriteByBrandId(String username, Integer bid, Pageable pageable) {
		return favoriteRepository.findFavoriteByBrandId(username,bid,pageable);
	}

	@Override
	public Favorite create(Favorite favorite) {
		return favoriteRepository.save(favorite);
	}


	@Override
	public void deleteById(Integer id) {
		favoriteRepository.deleteById(id);
		
	}

	@Override
	public List<Favorite> findAll() {
		return favoriteRepository.findAll();
	}

	@Override
	public List<Product> findFavoriteProductByUser(String username) {
		return favoriteRepository.findFavoriteProductByUser(username);
	}

	@Override
	public void deleteByProduct_IdandUsername_Id(Integer product_id, String username_id) {
		favoriteRepository.deleteByProduct_IdandUsername_Id(product_id,username_id);
	}


}

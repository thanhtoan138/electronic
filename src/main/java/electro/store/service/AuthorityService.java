package electro.store.service;

import java.util.List;

import electro.store.entity.Authority;

public interface AuthorityService {
	public List<Authority> fillAll();

	public Authority create(Authority auth);
	
	public void delete(Integer id);

	public List<Authority> findAuthoritiesOfAdministrators();
}

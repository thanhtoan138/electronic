package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.Account;
import electro.store.entity.Authority;
import electro.store.repository.AccountRepository;
import electro.store.repository.AuthorityRepository;
import electro.store.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityRepository dao;
	@Autowired
	AccountRepository acdao;
	
	
	public List<Authority> fillAll() {
		return dao.findAll();
	}

	
	public Authority create(Authority auth) {	
		return dao.save(auth);
	}
	
	public void delete(Integer id) {
		dao.deleteById(id);
	}
	
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = acdao.getAdministrators();
		return dao.authoritiesOf(accounts);			
	}
}

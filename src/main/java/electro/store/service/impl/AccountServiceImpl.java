package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.Account;
import electro.store.entity.AuthenticationType;
import electro.store.repository.AccountRepository;
import electro.store.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository dao;
	
	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

	public List<Account> findAll() {
		return dao.findAll();
	}
	
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}

	@Override
	public Account getAccountByEmail(String email) {
		return dao.getAccountByEmail(email);
	}

	@Override
	public void createNewAccountAfterOAuthLoginSuccess(String email, String name,
			String oauth2ClientName) {
		AuthenticationType authType = AuthenticationType.valueOf(oauth2ClientName.toUpperCase());
		Account account = new Account();
		account.setEmail(email);
		account.setFullname(name);
		account.setUsername(name);
		account.setAuthType(authType);
		dao.save(account);
	}

	@Override
	public void updateAccountAfterOAuthLoginSuccess(Account account, String name, 
			String oauth2ClientName) {
		AuthenticationType authType = AuthenticationType.valueOf(oauth2ClientName.toUpperCase());
		account.setFullname(name);
		account.setAuthType(authType);
		dao.save(account);
	}
}

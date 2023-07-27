package electro.store.service;

import java.util.List;

import electro.store.entity.Account;

public interface AccountService {

	Account findById(String username);

	public List<Account> getAdministrators();

	public List<Account> findAll();

	Account getAccountByEmail(String email);

	void createNewAccountAfterOAuthLoginSuccess(String email, String name,
			String oauth2ClientName);

	void updateAccountAfterOAuthLoginSuccess(Account account, String name, 
			String oauth2ClientName);

}

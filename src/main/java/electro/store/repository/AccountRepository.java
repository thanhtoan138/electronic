package electro.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN('DIRE','STAF')")
	List<Account> getAdministrators();
	
	@Query("select a from Account a where a.email=?1")
	Account getAccountByEmail(String email);
}

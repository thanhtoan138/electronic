package electro.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import electro.store.entity.Account;
import electro.store.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);
}
